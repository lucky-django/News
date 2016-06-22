package com.fury.news.injector.component;

import android.content.Context;
import com.fury.news.NewsApplication;
import com.fury.news.api.pic.PicApi;
import com.fury.news.api.sports.SportsApi;
import com.fury.news.injector.module.ApiModule;
import com.fury.news.injector.module.ApplicationModule;
import com.fury.news.utils.RxBus;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by lucky-django on 16/6/8.
 */
@Singleton @Component(modules = { ApplicationModule.class, ApiModule.class })
public interface ApplicationComponent {

  Context getContext();

  RxBus getBus();

  PicApi getPicApi();

  SportsApi getSportsApi();

  void inject(NewsApplication application);
}
