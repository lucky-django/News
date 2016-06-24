package com.fury.news.ui.main;

import com.fury.news.injector.PerActivity;
import com.fury.news.injector.component.ApplicationComponent;
import com.fury.news.injector.module.ActivityModule;
import com.fury.news.ui.sports.SportsFragment;
import dagger.Component;

/**
 * Created by lucky-django on 16/6/22.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = { ActivityModule.class })
public interface MainComponent {
  void inject(SportsFragment fragment);
}
