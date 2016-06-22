package com.fury.news;

import android.app.Application;
import com.fury.news.injector.component.ApplicationComponent;
import com.fury.news.injector.component.DaggerApplicationComponent;
import com.fury.news.injector.module.ApplicationModule;
import com.fury.news.utils.SystemUtils;
import com.fury.news.utils.ToastUtils;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by lucky-django on 16/6/8.
 */
public class NewsApplication extends Application {

  public ApplicationComponent mApplicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    String processName = SystemUtils.getAppName(this, android.os.Process.myPid());
    if (processName == null || !processName.equals(getPackageName())) {
      return;
    }
    mApplicationComponent =
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    ToastUtils.register(this);
    LeakCanary.install(this);
  }
}
