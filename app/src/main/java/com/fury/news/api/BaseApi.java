package com.fury.news.api;

/**
 * Created by lucky-django on 16/6/8.
 */
public class BaseApi {

  protected static final String API_HOST = "http://apis.baidu.com";

  protected String getHost() {
    return API_HOST;
  }
}
