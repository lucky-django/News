package com.fury.news.ui.main;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import butterknife.ButterKnife;
import com.fury.news.R;
import com.fury.news.api.sports.SportsApi;
import com.fury.news.model.sports.SportsResult;
import com.fury.news.ui.BaseActivity;
import com.google.gson.Gson;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {

  @Inject SportsApi mSportsApi;

  private MainComponent mMainComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle("My Title");

    setSupportActionBar(toolbar);


    mSportsApi.getInfo(10, 1)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<SportsResult>() {
          @Override public void call(SportsResult sportsResult) {
            Gson gson = new Gson();
            Log.e("news", gson.toJson(sportsResult, SportsResult.class));
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable throwable) {

          }
        });
  }

  @Override public void initInjector() {
    mMainComponent = DaggerMainComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
    mMainComponent.inject(this);
  }

  @Override public void initView() {
    ButterKnife.bind(this);
  }
}
