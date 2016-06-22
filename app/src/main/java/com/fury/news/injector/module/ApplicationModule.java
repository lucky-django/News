package com.fury.news.injector.module;

import android.content.Context;
import android.view.LayoutInflater;
import com.fury.news.network.HttpLoggingInterceptor;
import com.fury.news.utils.RxBus;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;

/**
 * Created by lucky-django on 16/6/8.
 */
@Module public class ApplicationModule {

  private final Context mContext;

  public ApplicationModule(Context context) {
    mContext = context;
  }

  @Provides @Singleton public Context provideApplicationContext() {
    return mContext.getApplicationContext();
  }

  @Provides @Singleton public RxBus provideRxBus() {
    return RxBus.getDefault();
  }

  @Provides @Singleton @Named("api") OkHttpClient provideApiOkHttpClient(
      HttpLoggingInterceptor httpLoggingInterceptor) {
    OkHttpClient.Builder builder =
        new OkHttpClient.Builder().connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
            .readTimeout(20 * 1000, TimeUnit.MILLISECONDS);
    builder.addInterceptor(httpLoggingInterceptor);
    return builder.build();
  }

  @Provides @Singleton OkHttpClient provideOkHttpClient(@Named("api") OkHttpClient okHttpClient,
      HttpLoggingInterceptor httpLoggingInterceptor) {
    OkHttpClient.Builder builder = okHttpClient.newBuilder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true);
    builder.interceptors().clear();
    builder.addInterceptor(httpLoggingInterceptor);
    return builder.build();
  }

  @Provides @Singleton HttpLoggingInterceptor provideHttpLogger() {
    return new HttpLoggingInterceptor();
  }

  @Provides @Singleton LayoutInflater provideLayoutInflater(Context context){
    return (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

}
