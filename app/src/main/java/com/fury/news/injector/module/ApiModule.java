package com.fury.news.injector.module;

import android.content.Context;
import com.fury.news.api.pic.PicApi;
import com.fury.news.api.sports.SportsApi;
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
  public PicApi providePicApi(@Named("api") OkHttpClient client, Context context) {
    return new PicApi(client, context);
  }

  @Provides @Singleton
  public SportsApi provideSportsApi(@Named("api") OkHttpClient client, Context context) {
    return new SportsApi(client, context);
  }
}
