package com.fury.news.network;

import com.fury.news.utils.LogUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpEngine;
import okio.Buffer;
import okio.BufferedSource;

public final class HttpLoggingInterceptor implements Interceptor {

  private static final Charset UTF8 = Charset.forName("UTF-8");
  private static final String TAG = "OkHttp";

  @Override public Response intercept(Chain chain) throws IOException {

    Request request = chain.request();
    RequestBody requestBody = request.body();
    boolean hasRequestBody = requestBody != null;

    Connection connection = chain.connection();
    Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
    String requestStartMessage =
        "--> " + request.method() + ' ' + request.url() + ' ' + protocol(protocol);
    if (hasRequestBody) {
      requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
    }
    LogUtils.i(TAG, requestStartMessage);

    if (hasRequestBody) {
      // Request body headers are only present when installed as a network interceptor. Force
      // them to be included (when available) so there values are known.
      if (requestBody.contentType() != null) {
        LogUtils.i(TAG, "Content-Type: " + requestBody.contentType());
      }
      if (requestBody.contentLength() != -1) {
        LogUtils.i(TAG, "Content-Length: " + requestBody.contentLength());
      }
    }

    Headers headers = request.headers();
    for (int i = 0, count = headers.size(); i < count; i++) {
      String name = headers.name(i);
      // Skip headers from the request body as they are explicitly logged above.
      if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
        LogUtils.i(TAG, name + ": " + headers.value(i));
      }
    }

    if (!hasRequestBody) {
      LogUtils.i(TAG, "--> END " + request.method());
    } else if (bodyEncoded(request.headers())) {
      LogUtils.i(TAG, "--> END " + request.method() + " (encoded body omitted)");
    } else {
      Buffer buffer = new Buffer();
      requestBody.writeTo(buffer);
      Charset charset = UTF8;
      MediaType contentType = requestBody.contentType();
      if (contentType != null) {
        charset = contentType.charset(UTF8);
      }
      LogUtils.i(TAG, "");
      LogUtils.i(TAG, buffer.readString(charset));
      LogUtils.i(TAG,
          "--> END " + request.method() + " (" + requestBody.contentLength() + "-byte body)");
    }

    long startNs = System.nanoTime();
    Response response = chain.proceed(request);
    long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

    ResponseBody responseBody = response.body();
    long contentLength = responseBody.contentLength();
    LogUtils.i(TAG, "<-- "
        + response.code()
        + ' '
        + response.message()
        + ' '
        + response.request().url()
        + " ("
        + tookMs
        + "ms"
        + ')');
    Headers bodyHeaders = response.headers();
    for (int i = 0, count = bodyHeaders.size(); i < count; i++) {
      LogUtils.i(TAG, bodyHeaders.name(i) + ": " + bodyHeaders.value(i));
    }

    if (!HttpEngine.hasBody(response)) {
      LogUtils.i(TAG, "<-- END HTTP");
    } else if (bodyEncoded(response.headers())) {
      LogUtils.i(TAG, "<-- END HTTP (encoded body omitted)");
    } else {
      BufferedSource source = responseBody.source();
      source.request(Long.MAX_VALUE); // Buffer the entire body.
      Buffer buffer = source.buffer();

      Charset charset = UTF8;
      MediaType contentType = responseBody.contentType();
      if (contentType != null) {
        charset = contentType.charset(UTF8);
      }

      if (contentLength != 0) {
        LogUtils.i(TAG, "");
        LogUtils.i(TAG, buffer.clone().readString(charset));
      }

      LogUtils.i(TAG, "<-- END HTTP (" + buffer.size() + "-byte body)");
    }

    return response;
  }

  private boolean bodyEncoded(Headers headers) {
    String contentEncoding = headers.get("Content-Encoding");
    return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
  }

  private static String protocol(Protocol protocol) {
    return protocol == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1";
  }
}
