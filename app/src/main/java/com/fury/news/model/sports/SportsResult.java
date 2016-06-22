package com.fury.news.model.sports;

import com.fury.news.model.BaseResult;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by lucky-django on 16/6/22.
 */
public class SportsResult extends BaseResult {

  @SerializedName("newslist") public List<SportsDetail> detailList;

}
