package com.fury.news.injector.module;

import android.app.Activity;
import com.fury.news.injector.PerActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by lucky-django on 16/6/8.
 */
@Module public class ActivityModule {

  private final Activity mActivity;

  public ActivityModule(Activity activity) {
    mActivity = activity;
  }

  @Provides @PerActivity public Activity provideActivity() {
    return mActivity;
  }
}
