package com.fury.news.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.fury.news.NewsApplication;
import com.fury.news.injector.component.ApplicationComponent;
import com.fury.news.injector.module.ActivityModule;

/**
 * Created by lucky-django on 16/6/8.
 */
public abstract class BaseActivity extends AppCompatActivity{

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

  protected ApplicationComponent getApplicationComponent() {
    return ((NewsApplication) getApplication()).mApplicationComponent;
  }

  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }

}
