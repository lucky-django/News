package com.fury.news.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lucky-django on 16/5/30.
 */
public class MenuItem implements Cloneable {

  //    @SerializedName("link_res_type")
  //    public int linkResType;
  //
  //    @SerializedName("link_res_id")
  //    public String linkResId;
  //
  //    @SerializedName("link_res_name")
  //    public String linkResName;

  /**
   * 菜单文字
   */
  @SerializedName("title") public String title;

  /**
   * 菜单是否被选中
   */
  @SerializedName("selected") public boolean selected;

  /**
   * 菜单跳转url
   */
  @SerializedName("link") public String jumpUrl;

  /**
   * 菜单icon
   */
  @SerializedName("icon") public String icon;
}
