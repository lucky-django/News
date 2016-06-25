package com.fury.news;

import android.app.Application;
import android.view.LayoutInflater;
import com.fury.news.injector.component.ApplicationComponent;
import com.fury.news.injector.component.DaggerApplicationComponent;
import com.fury.news.injector.module.ApplicationModule;
import com.fury.news.utils.SystemUtils;
import com.fury.news.utils.ToastUtils;
import com.squareup.leakcanary.LeakCanary;
import javax.inject.Inject;
import okhttp3.OkHttpClient;

/**
 * Created by lucky-django on 16/6/8.
 */
public class NewsApplication extends Application {

  public ApplicationComponent mApplicationComponent;
  @Inject public LayoutInflater mInflater;
  @Inject public OkHttpClient mOkHttpClient;

  public static NewsApplication mInstance;

  @Override public void onCreate() {
    super.onCreate();
    String processName = SystemUtils.getAppName(this, android.os.Process.myPid());
    if (processName == null || !processName.equals(getPackageName())) {
      return;
    }
    mInstance = this;
    mApplicationComponent =
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    mApplicationComponent.inject(this);
    ToastUtils.register(this);
    LeakCanary.install(this);
  }
}
