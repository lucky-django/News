package com.fury.news.injector.component;

import android.app.Activity;
import com.fury.news.injector.PerActivity;
import com.fury.news.injector.module.ActivityModule;
import dagger.Component;

/**
 * Created by lucky-django on 16/6/8.
 */
@PerActivity @Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

  Activity getActivity();
}
