package com.fury.news.injector.module;

import android.content.Context;
import com.fury.news.api.account.AccountApi;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;

/**
 * Created by lucky-django on 16/6/8.
 */
@Module public class ApiModule {

  @Provides @Singleton
  public AccountApi provideAccountApi(@Named("api") OkHttpClient okHttpClient, Context context) {
    return new AccountApi(okHttpClient, context);
  }
}
