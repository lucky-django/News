package com.fury.news.api.pic;

import android.content.Context;
import com.fury.news.api.BaseApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by lucky-django on 16/6/8.
 */
public class PicApi extends BaseApi {

  private PicService mPicService;

  public PicApi(OkHttpClient okHttpClient, Context context) {
    Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(getHost())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
    mPicService = retrofit.create(PicService.class);
  }

  public Observable<Object> getInfo(int num, int page) {
    return mPicService.getInfo(num, page).subscribeOn(Schedulers.io());
  }
}
