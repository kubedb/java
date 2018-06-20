package com.kubedb.client.util.credentials;

import com.kubedb.client.ApiClient;
import java.nio.charset.Charset;
import okio.ByteString;

/**
 * Uses Username and Password to configure {@link ApiClient} authentication to the Kubernetes API.
 */
public class UsernamePasswordAuthentication implements Authentication {
  private final String username;
  private final String password;

  public UsernamePasswordAuthentication(final String username, final String password) {
    this.username = username;
    this.password = password;
  }

  @Override
  public void provide(ApiClient client) {
    final String usernameAndPassword = username + ":" + password;
    client.setApiKeyPrefix("Basic");
    client.setApiKey(
        ByteString.of(usernameAndPassword.getBytes(Charset.forName("ISO-8859-1"))).base64());
  }
}
