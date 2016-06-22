package com.fury.news.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by lucky-django on 16/5/30.
 */
public class NaviDataDetail {
  public static final int NAVI_OLD = 0;
  public static final int NAVI_LEFT_DRAWER = 1;
  public static final int NAVI_RIGHT_DRAWER = 2;
  public static final int NAVI_RIGHT_DRAWER_NO_TOP_BAR = 3;
  public static final int NAVI_BOTTOM_BAR = 4;
  public static final int NAVI_POPUP_GRID = 5;
  public static final int NAVI_POPUP_LIST = 6;
  public static final int NAVI_SECOND = 7;

  /**
   * 相关view透明度
   */
  @SerializedName("opacity") public float alpha;

  /**
   * 导航菜单内容
   */
  @SerializedName("items") public List<MenuItem> menuItemList;

  /**
   * 导航类型
   */
  @SerializedName("nav_theme_type") public String naviType;

  /**
   * 是否有更多按钮
   */
  @SerializedName("nav_icon_more") public boolean hasMoreIcon;

  /**
   * 是否应该显示logo
   */
  @SerializedName("display_logo") public boolean shouldShowLogo;

  /**
   * 是否应该显示标题
   */
  @SerializedName("display_name") public boolean shouldShowTitle;

  /**
   * 是否应该显示登陆
   */
  @SerializedName("display_login_entrance") public boolean shouldShowLogin;

  /**
   * 是否应该显示底部icon
   */
  @SerializedName("display_list_icon") public boolean shouldShowIcon;

  /**
   * 头部的文字位置
   */
  @SerializedName("header_align") public String headerAlign;

  public int getNaviType() {
    if (naviType != null) {
      if (naviType.equals("t0")) {
        return NAVI_OLD;
      } else if (naviType.equals("t1")) {
        return NAVI_LEFT_DRAWER;
      } else if (naviType.equals("t2")) {
        return NAVI_RIGHT_DRAWER;
      } else if (naviType.equals("t3")) {
        return NAVI_RIGHT_DRAWER_NO_TOP_BAR;
      } else if (naviType.equals("t4")) {
        return NAVI_BOTTOM_BAR;
      } else if (naviType.equals("t5")) {
        return NAVI_POPUP_GRID;
      } else if (naviType.equals("t6")) {
        return NAVI_POPUP_LIST;
      }
    }
    return NAVI_OLD;
  }
}
