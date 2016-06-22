package com.fury.news.ui.main;

import com.fury.news.ui.BasePresenter;
import com.fury.news.ui.BaseView;

/**
 * Created by lucky-django on 16/6/22.
 */
public interface MainContract {

  interface View extends BaseView {

  }

  interface Presenter extends BasePresenter<View> {

  }
}
