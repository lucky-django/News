package com.fury.news.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lucky-django on 16/5/30.
 */
public class NaviInfo {

  /** 请求状态码 */
  @SerializedName("ret") public int ret;

  /** 请求结果消息 */
  @SerializedName("msg") public String msg;

  /** 数据域 */
  @SerializedName("data") public NaviData data;
}
