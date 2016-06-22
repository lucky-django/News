package com.fury.news.model;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lucky-django on 16/6/22.
 */
public class BaseResult {

  @SerializedName("code") public int code;

  @SerializedName("msg") public String msg;

  public boolean isSuccess() {
    if (TextUtils.isEmpty(msg)) {
      return false;
    }
    return code == 200 && msg.equals("success");
  }
}
