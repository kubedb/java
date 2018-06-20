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

import static com.kubedb.client.util.KubeConfig.*;

import com.kubedb.client.ApiClient;
import com.kubedb.client.util.credentials.AccessTokenAuthentication;
import com.kubedb.client.util.credentials.Authentication;
import com.kubedb.client.util.credentials.KubeconfigAuthentication;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** A Builder which allows the construction of {@link ApiClient}s in a fluent fashion. */
public class ClientBuilder {
  private static final Logger log = LoggerFactory.getLogger(ClientBuilder.class);

  private String basePath = Config.DEFAULT_FALLBACK_HOST;
  private byte[] caCertBytes = null;
  private boolean verifyingSsl = true;
  private Authentication authentication;

  /**
   * Creates an {@link ApiClient} by calling {@link #standard()} and {@link #build()}.
   *
   * @return An <tt>ApiClient</tt> configured using the precedence specified for {@link
   *     #standard()}.
   * @throws IOException if the configuration file or a file specified in a configuration file
   *     cannot be read.
   */
  public static ApiClient defaultClient() throws IOException {
    return ClientBuilder.standard().build();
  }

  /**
   * Creates a builder which is pre-configured in the following way
   *
   * <ul>
   *   <li>If $KUBECONFIG is defined, use that config file.
   *   <li>If $HOME/.kube/config can be found, use that.
   *   <li>If the in-cluster service account can be found, assume in cluster config.
   *   <li>Default to localhost:8080 as a last resort.
   * </ul>
   *
   * @return <tt>ClientBuilder</tt> pre-configured using the above precedence
   * @throws IOException if the configuration file or a file specified in a configuration file
   *     cannot be read.
   */
  public static ClientBuilder standard() throws IOException {
    final FileReader kubeConfigReader = findConfigFromEnv();
    if (kubeConfigReader != null) {
      return kubeconfig(loadKubeConfig(kubeConfigReader));
    }
    final FileReader configReader = findConfigInHomeDir();
    if (configReader != null) {
      return kubeconfig(loadKubeConfig(configReader));
    }
    final File clusterCa = new File(Config.SERVICEACCOUNT_CA_PATH);
    if (clusterCa.exists()) {
      return cluster();
    }
    return new ClientBuilder();
  }

  private static FileReader findConfigFromEnv() throws FileNotFoundException {
    String kubeConfigPath = System.getenv(Config.ENV_KUBECONFIG);
    if (kubeConfigPath == null) {
      return null;
    }
    final File kubeConfig = new File(kubeConfigPath);
    if (kubeConfig.exists()) {
      return new FileReader(kubeConfig);
    } else {
      log.debug("Could not find file specified in $KUBECONFIG");
      return null;
    }
  }

  private static FileReader findConfigInHomeDir() throws FileNotFoundException {
    final File config = new File(new File(System.getenv(ENV_HOME), KUBEDIR), KUBECONFIG);
    if (config.exists()) {
      return new FileReader(config);
    } else {
      log.debug("Could not find ~/.kube/config");
      return null;
    }
  }

  /**
   * Creates a builder which is pre-configured from the cluster configuration.
   *
   * @return <tt>ClientBuilder</tt> configured from the cluster configuration.
   * @throws IOException if the Service Account Token Path or CA Path is not readable.
   */
  public static ClientBuilder cluster() throws IOException {
    final ClientBuilder builder = new ClientBuilder();

    final String host = System.getenv(Config.ENV_SERVICE_HOST);
    final String port = System.getenv(Config.ENV_SERVICE_PORT);
    builder.setBasePath("https://" + host + ":" + port);

    final String token =
        new String(
            Files.readAllBytes(Paths.get(Config.SERVICEACCOUNT_TOKEN_PATH)),
            Charset.defaultCharset());
    builder.setCertificateAuthority(Files.readAllBytes(Paths.get(Config.SERVICEACCOUNT_CA_PATH)));
    builder.setAuthentication(new AccessTokenAuthentication(token));

    return builder;
  }

  /**
   * Creates a builder which is pre-configured from a {@link KubeConfig}.
   *
   * <p>To load a <tt>KubeConfig</tt>, see {@link KubeConfig#loadKubeConfig(Reader)}.
   *
   * @param config The {@link KubeConfig} to configure the builder from.
   * @return <tt>ClientBuilder</tt> configured from the provided <tt>KubeConfig</tt>
   * @throws IOException if the files specified in the provided <tt>KubeConfig</tt> are not readable
   */
  public static ClientBuilder kubeconfig(KubeConfig config) throws IOException {
    final ClientBuilder builder = new ClientBuilder();

    String server = config.getServer();
    if (!server.contains("://")) {
      if (server.contains(":443")) {
        server = "https://" + server;
      } else {
        server = "http://" + server;
      }
    }

    final byte[] caBytes =
        KubeConfig.getDataOrFile(
            config.getCertificateAuthorityData(), config.getCertificateAuthorityFile());
    if (caBytes != null) {
      builder.setCertificateAuthority(caBytes);
    }
    builder.setVerifyingSsl(config.verifySSL());

    builder.setBasePath(server);
    builder.setAuthentication(new KubeconfigAuthentication(config));
    return builder;
  }

  public String getBasePath() {
    return basePath;
  }

  public ClientBuilder setBasePath(String basePath) {
    this.basePath = basePath;
    return this;
  }

  public Authentication getAuthentication() {
    return authentication;
  }

  public ClientBuilder setAuthentication(final Authentication authentication) {
    this.authentication = authentication;
    return this;
  }

  public ClientBuilder setCertificateAuthority(final byte[] caCertBytes) {
    this.caCertBytes = caCertBytes;
    return this;
  }

  public boolean isVerifyingSsl() {
    return verifyingSsl;
  }

  public ClientBuilder setVerifyingSsl(boolean verifyingSsl) {
    this.verifyingSsl = verifyingSsl;
    return this;
  }

  public ApiClient build() {
    final ApiClient client = new ApiClient();

    if (basePath != null) {
      if (basePath.endsWith("/")) {
        basePath = basePath.substring(0, basePath.length() - 1);
      }
      client.setBasePath(basePath);
    }

    client.setVerifyingSsl(verifyingSsl);

    if (authentication != null) {
      authentication.provide(client);
    }

    // NOTE: this ordering is important.  The API Client re-evaluates the CA certificate every
    // time the SSL info changes, which means that if this comes after the following call
    // you will try to load a certificate with an exhausted InputStream. So setting the CA
    // certificate _has_ to be the last thing that you do related to SSL.
    //
    // TODO: this (imho) is broken in the generate Java Swagger Client code. We should fix it
    // upstream and remove this dependency.
    //
    // TODO: Add a test to ensure that this works correctly...
    if (caCertBytes != null) {
      client.setSslCaCert(new ByteArrayInputStream(caCertBytes));
    }

    return client;
  }
}
