package com.fury.news.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.fury.news.R;
import com.fury.news.injector.HasComponent;

/**
 * Created by lucky-django on 16/6/12.
 */
public abstract class BaseFragment extends Fragment {

  FrameLayout mContentLayout;
  View mProgressLayout;
  View mErrorLayout;

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return getContentView(inflater);
  }

  protected View getContentView(LayoutInflater inflater) {
    View root = inflater.inflate(R.layout.fragment_base, null);
    mContentLayout = (FrameLayout) root.findViewById(R.id.layout_content);
    mProgressLayout = root.findViewById(R.id.layout_progress);
    mErrorLayout = root.findViewById(R.id.layout_error);
    View content = inflater.inflate(getContentResId(), null);
    mContentLayout.addView(content, FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.MATCH_PARENT);
    return root;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initInjector();
    getBundle(getArguments());
    initView(view);
    getData();
  }

  protected abstract void initInjector();

  protected abstract @LayoutRes int getContentResId();

  protected abstract void getBundle(Bundle bundle);

  protected abstract void initView(View view);

  protected abstract void getData();

  protected void showProgressView() {
    mProgressLayout.setVisibility(View.VISIBLE);
  }

  protected void hideProgressView() {
    mProgressLayout.setVisibility(View.INVISIBLE);
  }

  protected void showErrorView() {
    mErrorLayout.setVisibility(View.VISIBLE);
  }

  protected void hideErrorView() {
    mErrorLayout.setVisibility(View.INVISIBLE);
  }

  @SuppressWarnings("unchecked") protected <C> C getComponent(Class<C> componentType) {
    return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
  }

}
