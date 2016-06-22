package com.fury.news.api.pic;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lucky-django on 16/6/8.
 */
public interface PicService {

  @GET("/txapi/mvtp/meinv") Observable<Object> getInfo(@Query("num") int num);
}
