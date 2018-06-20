/*
Copyright 2017, 2018 The Kubernetes Authors.
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

import static com.squareup.okhttp.ws.WebSocket.BINARY;
import static com.squareup.okhttp.ws.WebSocket.TEXT;

import com.google.common.net.HttpHeaders;
import com.kubedb.client.ApiClient;
import com.kubedb.client.ApiException;
import com.kubedb.client.Pair;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.ws.WebSocket;
import com.squareup.okhttp.ws.WebSocketCall;
import com.squareup.okhttp.ws.WebSocketListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okio.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSockets {
  private static final Logger log = LoggerFactory.getLogger(WebSockets.class);

  // Only support v4 stream protocol as it was available since k8s 1.4
  public static final String V4_STREAM_PROTOCOL = "v4.channel.k8s.io";
  public static final String STREAM_PROTOCOL_HEADER = "Sec-WebSocket-Protocol";
  public static final String SPDY_3_1 = "SPDY/3.1";

  /** A simple interface for a listener on a web socket */
  public interface SocketListener {
    /** Called when the socket is opened */
    public void open(String protocol, WebSocket socket);

    /**
     * Called when a binary media type message is received
     *
     * @param in The input stream containing the binary data
     */
    public void bytesMessage(InputStream in);

    /**
     * Called when a text media type message is received
     *
     * @param in The character stream containing the message
     */
    public void textMessage(Reader in);

    /** Called when the stream is closed. */
    public void close();
  }

  /**
   * Create a new WebSocket stream
   *
   * @param path The HTTP Path to request from the API
   * @param method The HTTP method to use for the call
   * @param client The ApiClient for communicating with the API
   * @param listener The socket listener to handle socket events
   */
  public static void stream(String path, String method, ApiClient client, SocketListener listener)
      throws ApiException, IOException {
    stream(path, method, new ArrayList<Pair>(), client, listener);
  }

  public static void stream(
      String path, String method, List<Pair> queryParams, ApiClient client, SocketListener listener)
      throws ApiException, IOException {

    HashMap<String, String> headers = new HashMap<String, String>();
    headers.put(STREAM_PROTOCOL_HEADER, V4_STREAM_PROTOCOL);
    headers.put(HttpHeaders.CONNECTION, HttpHeaders.UPGRADE);
    headers.put(HttpHeaders.UPGRADE, SPDY_3_1);
    String[] localVarAuthNames = new String[] {"BearerToken"};

    Request request =
        client.buildRequest(
            path,
            method,
            queryParams,
            new ArrayList<Pair>(),
            null,
            headers,
            new HashMap<String, Object>(),
            localVarAuthNames,
            null);
    streamRequest(request, client, listener);
  }

  /*
  If we ever upgrade to okhttp 3...
  public static void stream(Call call, ApiClient client, SocketListener listener) {
      streamRequest(call.request(), client, listener);
  }
  */

  private static void streamRequest(Request request, ApiClient client, SocketListener listener) {
    WebSocketCall.create(client.getHttpClient(), request).enqueue(new Listener(listener));
  }

  public static class Listener implements WebSocketListener {
    private SocketListener listener;

    public Listener(SocketListener listener) {
      this.listener = listener;
    }

    @Override
    public void onOpen(final WebSocket webSocket, Response response) {
      String protocol = response.header(STREAM_PROTOCOL_HEADER, "missing");
      listener.open(protocol, webSocket);
    }

    @Override
    public void onMessage(ResponseBody body) throws IOException {
      if (body.contentType() == TEXT) {
        listener.textMessage(body.charStream());
      } else if (body.contentType() == BINARY) {
        listener.bytesMessage(body.byteStream());
      }
      body.close();
    }

    @Override
    public void onPong(Buffer payload) {}

    @Override
    public void onClose(int code, String reason) {
      listener.close();
    }

    @Override
    public void onFailure(IOException e, Response res) {
      e.printStackTrace();
      listener.close();
    }
  }
}
