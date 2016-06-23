package com.fury.news.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

public class SnackBarUtils {

  public static Context mContext;

  private SnackBarUtils() {
  }

  public static void register(Context context) {
    mContext = context.getApplicationContext();
  }

  public static void showToast(View parent, int resId) {
    Snackbar.make(parent, mContext.getString(resId), Snackbar.LENGTH_SHORT).show();
  }

  public static void showToast(View parent, int resId, String action,
      View.OnClickListener listener) {
    Snackbar.make(parent, mContext.getString(resId), Snackbar.LENGTH_SHORT)
        .setAction(action, listener)
        .show();
  }

  public static void showToast(View parent, String msg) {
    Snackbar.make(parent, msg, Snackbar.LENGTH_SHORT).show();
  }
}
