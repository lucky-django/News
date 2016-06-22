package com.fury.news.api;

import com.sohu.kzapp.BuildConfig;

/**
 * Created by lucky-django on 16/6/8.
 */
public class BaseApi {

  protected static final String T1_HOST = "http://www.t1.com";
  protected static final String KUAIZHAN_HOST = "http://www.kuaizhan.com";

  protected String getHost() {
    if (BuildConfig.DEBUG) {
      return T1_HOST;
    } else {
      return KUAIZHAN_HOST;
    }
  }
}
