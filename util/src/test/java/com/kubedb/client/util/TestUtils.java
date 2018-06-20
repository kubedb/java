package com.kubedb.client.util;

import com.kubedb.client.ApiClient;
import com.kubedb.client.auth.ApiKeyAuth;

public class TestUtils {

  public static ApiKeyAuth getApiKeyAuthFromClient(ApiClient client) {
    return (ApiKeyAuth) client.getAuthentications().get("BearerToken");
  }
}
