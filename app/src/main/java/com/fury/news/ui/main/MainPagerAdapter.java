package com.fury.news.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import com.fury.news.ui.sports.SportsFragment;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucky-django on 16/6/24.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

  private List<WeakReference<Fragment>> fragmentList;
  private List<String> mTitles;

  public MainPagerAdapter(FragmentManager fm) {
    super(fm);
    fragmentList = new ArrayList<>();
    mTitles = new ArrayList<>();
    mTitles.add("体育");
  }

  @Override public Fragment getItem(int position) {
    if (position > fragmentList.size() - 1
        || fragmentList.get(position) == null
        || fragmentList.get(position).get() == null) {
      SportsFragment fragment = new SportsFragment();
      fragmentList.add(new WeakReference<Fragment>(fragment));
      return fragment;
    }
    return fragmentList.get(position).get();
  }

  @Override public int getCount() {
    return 1;
  }

  @Override public CharSequence getPageTitle(int position) {
    return mTitles.get(position);
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    super.destroyItem(container, position, object);
  }
}
