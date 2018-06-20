package com.kubedb.client.examples;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kubedb.client.ApiClient;
import com.kubedb.client.ApiException;
import com.kubedb.client.Configuration;
import com.kubedb.client.apis.KubedbComV1alpha1Api;
import com.kubedb.client.models.V1alpha1Redis;
import com.kubedb.client.util.ClientBuilder;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple Example of how to use the Java API.<br>
 * This example demonstrates patching of Redis using Json Patch.<br>
 * For generating Json Patches, refer <a href="http://jsonpatch.com/">http://jsonpatch.com</a>.
 *
 * <ul>
 *   <li>Creates Redis redis-quickstart with <b>spec.doNotPause</b> value as true.
 *   <li>Patches Redis redis-quickstart with <b>spec.doNotPause</b> value as false.
 * </ul>
 *
 * <p>Easiest way to run this: mvn exec:java
 * -Dexec.mainClass="com.kubedb.client.examples.PatchExample"
 *
 * <p>From inside $REPO_DIR/examples
 */
public class PatchExample {
  static String jsonPatchStr = "{\"op\":\"replace\",\"path\":\"/spec/doNotPause\",\"value\":false}";
  static String jsonDepStr =
      "{\"apiVersion\":\"kubedb.com/v1alpha1\",\"kind\":\"Redis\",\"metadata\":{\"name\":\"redis-quickstart\",\"namespace\":\"default\"},\"spec\":{\"version\":\"4\",\"doNotPause\":true,\"storage\":{\"storageClassName\":\"standard\",\"accessModes\":[\"ReadWriteOnce\"],\"resources\":{\"requests\":{\"storage\":\"50Mi\"}}}}}";

  public static void main(String[] args) throws IOException, ApiException {
    PatchExample example = new PatchExample();
    ApiClient client = ClientBuilder.defaultClient();
    Configuration.setDefaultApiClient(client);

    V1alpha1Redis body = (V1alpha1Redis) example.deserialize(jsonDepStr, V1alpha1Redis.class);
    V1alpha1Redis deploy1 = example.createRedis("default", body, "false");
    System.out.println("original Redis" + deploy1);

    ArrayList<JsonObject> arr = new ArrayList<>();
    arr.add(((JsonElement) example.deserialize(jsonPatchStr, JsonElement.class)).getAsJsonObject());
    V1alpha1Redis deploy2 = example.PatchRedis("redis-quickstart", "default", arr, "false");
    System.out.println("patched Redis" + deploy2);
  }

  public V1alpha1Redis createRedis(String namespace, V1alpha1Redis body, String pretty)
      throws ApiException {
    KubedbComV1alpha1Api api = new KubedbComV1alpha1Api();
    V1alpha1Redis deploy = api.createNamespacedRedis(namespace, body, pretty);
    return deploy;
  }

  public V1alpha1Redis PatchRedis(String deployName, String namespace, Object body, String pretty)
      throws ApiException {
    KubedbComV1alpha1Api api = new KubedbComV1alpha1Api();
    V1alpha1Redis deploy = api.patchNamespacedRedis(deployName, namespace, body, pretty);
    return deploy;
  }

  public Object deserialize(String jsonStr, Class<?> targetClass) {
    Object obj = (new Gson()).fromJson(jsonStr, targetClass);
    return obj;
  }
}
