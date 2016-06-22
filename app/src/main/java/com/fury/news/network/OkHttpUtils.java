package com.fury.news.network;

import java.io.IOException;
import java.util.HashMap;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lucky-django on 16/6/22.
 */
public class OkHttpUtils {

  public static void addHeader(OkHttpClient.Builder builder,
      final String headerName, final String headerContent) {
    builder.interceptors().add(new Interceptor() {
      @Override public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader(headerName, headerContent);
        return chain.proceed(builder.build());
      }
    });
  }

  public static void addHeaders( OkHttpClient.Builder builder,
      final HashMap<String, String> headerMap) {
    builder.interceptors().add(new Interceptor() {
      @Override public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        for (String headerName : headerMap.keySet()) {
          builder.addHeader(headerName, headerMap.get(headerName));
        }
        return chain.proceed(builder.build());
      }
    });
  }
}
