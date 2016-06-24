package com.fury.news.api.sports;

import com.fury.news.model.sports.SportsResult;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lucky-django on 16/6/22.
 */
public interface SportsService {
  
  @GET("/txapi/tiyu/tiyu") Observable<SportsResult> getInfo(@Query("num") int num,
      @Query("page") int page);
}
