/*
Copyright 2017 The Kubernetes Authors.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.kubedb.client.util;

import static org.junit.Assert.*;

import com.kubedb.client.util.authenticators.GCPAuthenticator;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/** Tests for the KubeConfigConfig helper class */
public class KubeConfigTest {

  @Rule public TemporaryFolder folder = new TemporaryFolder();

  public static String KUBECONFIG_TOKEN =
      "apiVersion: v1\n"
          + "clusters:\n"
          + "- cluster:\n"
          + "    server: http://kubeconfig.dir.com\n"
          + "  name: foo\n"
          + "users:\n"
          + "- user:\n"
          + "   token: foobaz\n"
          + "  name: foo\n"
          + "contexts:\n"
          + "- context:\n"
          + "    cluster: foo\n"
          + "    namespace: some_namespace\n"
          + "    user: foo\n"
          + "  name: foo-context\n"
          + "current-context: foo-context\n";

  @Test
  public void testToken() {
    KubeConfig config = KubeConfig.loadKubeConfig(new StringReader(KUBECONFIG_TOKEN));
    assertEquals(config.getAccessToken(), "foobaz");
  }

  @Test
  public void testTokenFile() throws IOException {
    String token = "flubble";
    File tokenFile = folder.newFile("token-file.txt");
    Files.write(tokenFile.toPath(), token.getBytes("UTF-8"));

    String replace = KUBECONFIG_TOKEN.replace("foobaz", tokenFile.getCanonicalPath());
    replace = replace.replace("token:", "tokenFile:");
    KubeConfig config = KubeConfig.loadKubeConfig(new StringReader(replace));
    assertEquals(config.getAccessToken(), token);
  }

  public static String GCP_CONFIG =
      "apiVersion: v1\n"
          + "contexts:\n"
          + "- context:\n"
          + "    user: gke-cluster\n"
          + "  name: foo-context\n"
          + "current-context: foo-context\n"
          + "users:\n"
          + "- name: gke-cluster\n"
          + "  user:\n"
          + "    auth-provider:\n"
          + "      config:\n"
          + "        access-token: fake-token\n"
          + "        cmd-args: config config-helper --format=json\n"
          + "        cmd-path: /usr/lib/google-cloud-sdk/bin/gcloud\n"
          + "        expiry: 2117-06-22T18:27:02Z\n"
          + "        expiry-key: '{.credential.token_expiry}'\n"
          + "        token-key: '{.credential.access_token}'\n"
          + "      name: gcp\n";

  @Test
  public void testGCPAuthProvider() {
    KubeConfig.registerAuthenticator(new GCPAuthenticator());

    try {
      File config = folder.newFile("config");
      FileWriter writer = new FileWriter(config);
      writer.write(GCP_CONFIG);
      writer.flush();
      writer.close();

      KubeConfig kc = KubeConfig.loadKubeConfig(new FileReader(config));
      assertEquals("fake-token", kc.getAccessToken());
    } catch (Exception ex) {
      ex.printStackTrace();
      fail("Unexpected exception: " + ex);
    }
  }

  @Test
  public void testNamespace() {
    KubeConfig config = KubeConfig.loadKubeConfig(new StringReader(KUBECONFIG_TOKEN));
    assertEquals(config.getNamespace(), "some_namespace");
  }

  @Test
  public void testNullNamespace() {
    KubeConfig config = KubeConfig.loadKubeConfig(new StringReader(GCP_CONFIG));
    assertNull(config.getNamespace());
  }
}
