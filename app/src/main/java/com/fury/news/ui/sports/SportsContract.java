package com.fury.news.ui.sports;

import com.fury.news.model.sports.SportsDetail;
import com.fury.news.ui.BasePresenter;
import com.fury.news.ui.BaseView;
import java.util.List;

/**
 * Created by lucky-django on 16/6/22.
 */
public interface SportsContract {

  interface View extends BaseView {
    void showProgress();

    void hideProgress();

    void showError();

    void hideError();

    void renderData(List<SportsDetail> detailList);
  }

  interface Presenter extends BasePresenter<View> {
    void loadData();
  }
}
