package com.kubedb.client.util.credentials;

import com.kubedb.client.ApiClient;
import com.kubedb.client.util.ClientBuilder;

/**
 * Allows the implementation of different authentication mechanisms for the Kubernetes API.
 *
 * @see ClientBuilder#setAuthentication(Authentication)
 */
public interface Authentication {

  void provide(ApiClient client);
}
