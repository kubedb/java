package com.kubedb.client.examples;

import com.kubedb.client.ApiClient;
import com.kubedb.client.ApiException;
import com.kubedb.client.Configuration;
import com.kubedb.client.apis.KubedbComV1alpha1Api;
import com.kubedb.client.models.V1alpha1Redis;
import com.kubedb.client.models.V1alpha1RedisList;
import com.kubedb.client.util.Config;
import java.io.IOException;

/**
 * A simple example of how to use the Java API
 *
 * <p>Easiest way to run this: mvn exec:java -Dexec.mainClass="com.kubedb.client.examples.Example"
 *
 * <p>From inside $REPO_DIR/examples
 */
public class Example {
  public static void main(String[] args) throws IOException, ApiException {
    ApiClient client = Config.defaultClient();
    Configuration.setDefaultApiClient(client);

    KubedbComV1alpha1Api api = new KubedbComV1alpha1Api();
    V1alpha1RedisList list =
        api.listRedisForAllNamespaces(null, null, null, null, null, null, null, null, null);
    for (V1alpha1Redis item : list.getItems()) {
      System.out.println(item.getMetadata().getName());
    }
  }
}
