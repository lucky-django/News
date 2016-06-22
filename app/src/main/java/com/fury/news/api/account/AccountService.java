package com.fury.news.api.account;

import com.fury.news.model.NaviInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lucky-django on 16/6/8.
 */
public interface AccountService {

  //@GET("/site/ajax-site-public-info") Observable<NaviInfo> getNaviInfo(@Query("site_id") String siteId);

  @GET("/site/ajax-site-public-info") Call<NaviInfo> getNaviInfo(@Query("site_id") String siteId);

}
