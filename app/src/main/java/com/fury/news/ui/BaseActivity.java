package com.fury.news.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import com.fury.news.NewsApplication;
import com.fury.news.R;
import com.fury.news.injector.component.ApplicationComponent;
import com.fury.news.injector.module.ActivityModule;

/**
 * Created by lucky-django on 16/6/8.
 */
public abstract class BaseActivity extends AppCompatActivity {

  private FrameLayout mMainLayout;
  private View mProgressLayout;
  private View mErrorLayout;
  Animation mShowAnim;
  Animation mHideAnim;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_base);
    mMainLayout = (FrameLayout) findViewById(R.id.layout_content);
    mProgressLayout = findViewById(R.id.layout_progress);
    mErrorLayout = findViewById(R.id.layout_error);
    addContentView(getContentRes());
    initView();
    initInjector();
  }

  /**
   * 添加自己的布局
   */
  private void addContentView(@LayoutRes int resid) {
    View contentView = getLayoutInflater().inflate(resid, null);
    mMainLayout.addView(contentView, FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.MATCH_PARENT);
  }

  protected abstract @LayoutRes int getContentRes();

  /**
   * 注入Injector
   */
  protected abstract void initInjector();

  /**
   * 视图初始化
   */
  protected abstract void initView();

  protected void showProgressView() {
    mProgressLayout.setVisibility(View.VISIBLE);
  }

  protected void hideProgressView() {
    mProgressLayout.setVisibility(View.INVISIBLE);
  }

  protected void showErrorView() {
    mErrorLayout.setVisibility(View.VISIBLE);
  }

  protected void hideErrorView() {
    mErrorLayout.setVisibility(View.INVISIBLE);
  }

  protected ApplicationComponent getApplicationComponent() {
    return ((NewsApplication) getApplication()).mApplicationComponent;
  }

  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }
}
