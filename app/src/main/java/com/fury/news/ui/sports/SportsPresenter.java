package com.fury.news.ui.sports;

import android.support.annotation.NonNull;
import android.util.Log;
import com.fury.news.api.sports.SportsApi;
import com.fury.news.injector.PerActivity;
import com.fury.news.model.sports.SportsResult;
import com.google.gson.Gson;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by lucky-django on 16/6/24.
 */
@PerActivity public class SportsPresenter implements SportsContract.Presenter {

  private SportsApi mSportsApi;
  private Subscription mSubscription;
  private SportsContract.View mSportsView;

  @Inject public SportsPresenter(SportsApi api) {
    mSportsApi = api;
  }

  @Override public void attachView(@NonNull SportsContract.View view) {
    mSportsView = view;
  }

  @Override public void detachView() {
    if (mSubscription != null && !mSubscription.isUnsubscribed()) {
      mSubscription.unsubscribe();
    }
    mSportsView = null;
  }

  @Override public void loadData() {
    mSportsView.showProgress();
    mSubscription = mSportsApi.getInfo(10, 0)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<SportsResult>() {
          @Override public void call(SportsResult sportsResult) {
            mSportsView.hideProgress();
            if (sportsResult.isSuccess()) {
              mSportsView.hideError();
              Gson gson = new Gson();
              String json = gson.toJson(sportsResult);
              Log.e("news", json);
              mSportsView.renderData(sportsResult.detailList);
            } else {
              mSportsView.showError();
            }
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable throwable) {
            Log.e("news", throwable.getMessage());
            mSportsView.hideProgress();
            mSportsView.showError();
          }
        });
  }
}
