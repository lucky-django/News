package com.fury.news.api.sports;

import android.content.Context;
import com.fury.news.api.BaseApi;
import com.fury.news.model.sports.SportsResult;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by lucky-django on 16/6/22.
 */
public class SportsApi extends BaseApi {

  private SportsService mSportsService;

  public SportsApi(OkHttpClient okHttpClient, Context context) {
    Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(getHost())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
    mSportsService = retrofit.create(SportsService.class);
  }

  public Observable<SportsResult> getInfo(int num, int page) {
    return mSportsService.getInfo(num, page).subscribeOn(Schedulers.io());
  }
}
