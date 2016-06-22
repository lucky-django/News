package com.fury.news.injector.module;

import android.app.Service;
import com.fury.news.injector.PerService;
import dagger.Module;
import dagger.Provides;

/**
 * Created by lucky-django on 16/6/8.
 */
@Module public class ServiceModule {

  private Service mService;

  public ServiceModule(Service service) {
    mService = service;
  }

  @Provides @PerService public Service provideService() {
    return mService;
  }
}
