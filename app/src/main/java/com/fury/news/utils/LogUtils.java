package com.fury.news.utils;

import android.util.Log;
import com.fury.news.BuildConfig;

/**
 * Created by lucky-django on 16/6/8.
 */
public class LogUtils {

  public static void i(String tag, String message) {
    if (BuildConfig.DEBUG) {
      Log.i(tag, message);
    }
  }

  public static void w(String tag, String message) {
    if (BuildConfig.DEBUG) {
      Log.w(tag, message);
    }
  }

  public static void e(String tag, String message) {
    if (BuildConfig.DEBUG) {
      Log.e(tag, message);
    }
  }
}
