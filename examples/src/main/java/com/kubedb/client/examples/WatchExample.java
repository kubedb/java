package com.kubedb.client.examples;

import com.google.gson.reflect.TypeToken;
import com.kubedb.client.ApiClient;
import com.kubedb.client.ApiException;
import com.kubedb.client.Configuration;
import com.kubedb.client.apis.KubedbComV1alpha1Api;
import com.kubedb.client.models.V1alpha1Redis;
import com.kubedb.client.util.Config;
import com.kubedb.client.util.Watch;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/** A simple example of how to use Watch API to watch changes in Redis list. */
public class WatchExample {
  public static void main(String[] args) throws IOException, ApiException {
    ApiClient client = Config.defaultClient();
    client.getHttpClient().setReadTimeout(60, TimeUnit.SECONDS);
    Configuration.setDefaultApiClient(client);

    KubedbComV1alpha1Api api = new KubedbComV1alpha1Api();

    Watch<V1alpha1Redis> watch =
        Watch.createWatch(
            client,
            api.listRedisForAllNamespacesCall(
                null, null, null, null, null, "false", null, null, Boolean.TRUE, null, null),
            new TypeToken<Watch.Response<V1alpha1Redis>>() {}.getType());

    for (Watch.Response<V1alpha1Redis> item : watch) {
      System.out.printf("%s : %s%n", item.type, item.object.getMetadata().getName());
    }
  }
}
