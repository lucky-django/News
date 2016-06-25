package com.fury.news.ui.sports;

import android.support.annotation.NonNull;
import android.util.Log;
import com.fury.news.Constants;
import com.fury.news.api.pic.PicApi;
import com.fury.news.api.sports.SportsApi;
import com.fury.news.injector.PerActivity;
import com.fury.news.model.sports.SportsResult;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by lucky-django on 16/6/24.
 */
@PerActivity public class SportsPresenter implements SportsContract.Presenter {

  private SportsApi mSportsApi;
  private Subscription mSubscription;
  private SportsContract.View mSportsView;
  private CompositeSubscription mCompositeSubscription = new CompositeSubscription();
  private int mPage = 1;

  @Inject public SportsPresenter(SportsApi api, PicApi picApi) {
    mSportsApi = api;
  }

  @Override public void attachView(@NonNull SportsContract.View view) {
    mSportsView = view;
  }

  @Override public void detachView() {
    mCompositeSubscription.unsubscribe();
    mSportsView = null;
  }

  @Override public void loadData() {
    mSportsView.showProgress();
    mPage = 1;
    mSubscription = mSportsApi.getInfo(Constants.ITEM_NUM, mPage)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<SportsResult>() {
          @Override public void call(SportsResult sportsResult) {
            mSportsView.hideProgress();
            mSportsView.refreshComplete();
            if (sportsResult.isSuccess()) {
              mSportsView.hideError();
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
    mCompositeSubscription.add(mSubscription);
  }

  @Override public void refreshData() {
    loadData();
  }

  @Override public void loadNextPage() {
    mPage++;
    mSubscription = mSportsApi.getInfo(Constants.ITEM_NUM, mPage)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<SportsResult>() {
          @Override public void call(SportsResult sportsResult) {
            mSportsView.hideProgress();
            mSportsView.loadMoreComplete();
            if (sportsResult.isSuccess()) {
              mSportsView.hideError();
              mSportsView.addData(sportsResult.detailList);
            } else {
              mSportsView.showError();
            }
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable throwable) {
            Log.e("news", throwable.getMessage());
            mSportsView.hideProgress();
            mSportsView.loadMoreComplete();
            mSportsView.showError();
          }
        });
  }
}
