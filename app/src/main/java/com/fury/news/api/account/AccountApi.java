package com.fury.news.api.account;

import android.content.Context;
import com.fury.news.api.BaseApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lucky-django on 16/6/8.
 */
public class AccountApi extends BaseApi {

  private AccountService mAccountService;

  public AccountApi(OkHttpClient okHttpClient, Context context) {
    Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(getHost())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
    mAccountService = retrofit.create(AccountService.class);
  }

  //public Observable<NaviInfo> getNaviInfo(String siteId) {
  //  return mAccountService.getNaviInfo(siteId).subscribeOn(Schedulers.io());
  //}
}
