package com.fury.news.ui.sports;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.fury.news.R;
import com.fury.news.model.sports.SportsDetail;
import com.fury.news.ui.BaseFragment;
import com.fury.news.ui.main.MainComponent;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by lucky-django on 16/6/24.
 */
public class SportsFragment extends BaseFragment implements SportsContract.View {

  @Inject SportsPresenter mPresenter;
  @BindView(R.id.recycler) RecyclerView mRecyclerView;
  private SportsAdapter mAdapter;

  public static SportsFragment newInstance() {
    return new SportsFragment();
  }

  @Override protected void initInjector() {
    getComponent(MainComponent.class).inject(this);
  }

  @Override protected int getContentResId() {
    return R.layout.fragment_sports;
  }

  @Override protected void getBundle(Bundle bundle) {
  }

  @Override protected void initView(View view) {
    ButterKnife.bind(this, view);
    mAdapter = new SportsAdapter(getActivity());
    LinearLayoutManager manager = new LinearLayoutManager(getActivity());
    manager.setOrientation(LinearLayoutManager.VERTICAL);
    mRecyclerView.setLayoutManager(manager);
    mRecyclerView.setAdapter(mAdapter);
    mPresenter.attachView(this);
  }

  @Override protected void getData() {
    mPresenter.loadData();
  }

  @Override public void showProgress() {
    showProgressView();
  }

  @Override public void hideProgress() {
    hideProgressView();
  }

  @Override public void showError() {
    showErrorView();
  }

  @Override public void hideError() {
    hideErrorView();
  }

  @Override public void renderData(List<SportsDetail> detailList) {
    mAdapter.bindData(detailList);
    Log.e("news",mAdapter.getItemCount() + "");
  }

  @Override public void onDestroy() {
    mPresenter.detachView();
    super.onDestroy();
  }
}
