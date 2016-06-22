package com.fury.news.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lucky-django on 16/5/30.
 */
public class NaviData {

  /** 站点名 */
  @SerializedName("site_name") public String siteName;

  /** 站点Url */
  @SerializedName("site_url") public String siteUrl;

  /** 站点logoUrl */
  @SerializedName("logo_url") public String logoUrl;

  /** 站点主题色 */
  @SerializedName("theme") public NaviTheme theme;

  /** 导航信息 */
  @SerializedName("nav") public NaviDataDetail navaDataDetail;

  @SerializedName("font_icon_url") public String fontUrl;
}
