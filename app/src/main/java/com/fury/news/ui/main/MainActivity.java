package com.fury.news.ui.main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.fury.news.R;
import com.fury.news.injector.HasComponent;
import com.fury.news.ui.BaseActivity;

public class MainActivity extends BaseActivity implements HasComponent<MainComponent> {

  private MainComponent mMainComponent;
  @BindView(R.id.toolbar) Toolbar mToolBar;
  @BindView(R.id.tabs) TabLayout mTabLayout;
  @BindView(R.id.viewpager) ViewPager mViewPager;

  @Override protected int getContentRes() {
    return R.layout.activity_main;
  }

  @Override public void initInjector() {
    mMainComponent = DaggerMainComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override public void initView() {
    ButterKnife.bind(this);
    setSupportActionBar(mToolBar);
    mViewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
    mTabLayout.setupWithViewPager(mViewPager);
  }

  @Override public MainComponent getComponent() {
    return mMainComponent;
  }
}
