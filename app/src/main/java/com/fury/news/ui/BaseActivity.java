package com.fury.news.ui;

import android.app.Activity;
import android.os.Bundle;
import com.fury.news.AppManager;

/**
 * Created by lucky-django on 16/6/8.
 */
public abstract class BaseActivity extends Activity{

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initInjector();
    initView();
  }
  /**
   * 注入Injector
   */
  public abstract void initInjector();

  /**
   * 视图初始化
   */
  public abstract void initView();

  @Override protected void onDestroy() {
    super.onDestroy();
    AppManager.getAppManager().finishActivity(this);
  }

}
