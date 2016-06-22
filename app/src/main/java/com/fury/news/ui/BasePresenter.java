package com.fury.news.ui;

import android.support.annotation.NonNull;

/**
 * Created by lucky-django on 16/6/22.
 */
public interface BasePresenter<T extends BaseView> {

  void attachView(@NonNull T view);

  void detachView();
}
