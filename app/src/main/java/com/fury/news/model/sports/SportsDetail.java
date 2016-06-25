package com.fury.news.model.sports;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lucky-django on 16/6/22.
 */
public class SportsDetail {

  @SerializedName("ctime") public String newsTime;

  @SerializedName("title") public String newsTitle;

  @SerializedName("description") public String origin;

  @SerializedName("picUrl") public String picUrl;

  @SerializedName("url") public String jumpUrl;
}
