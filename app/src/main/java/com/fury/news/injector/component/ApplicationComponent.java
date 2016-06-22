package com.fury.news.injector.component;

import android.content.Context;
import com.fury.news.KZApplication;
import com.fury.news.api.account.AccountApi;
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

  AccountApi getAccountApi();

  void inject(KZApplication application);
}
