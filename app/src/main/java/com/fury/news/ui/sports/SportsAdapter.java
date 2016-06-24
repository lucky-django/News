package com.fury.news.ui.sports;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.fury.news.NewsApplication;
import com.fury.news.R;
import com.fury.news.model.sports.SportsDetail;
import java.util.List;

/**
 * Created by lucky-django on 16/6/24.
 */
public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.ViewHolder> {

  private List<SportsDetail> mDetailList;
  private Context mContext;

  public SportsAdapter(Context context) {
    mContext = context;
  }

  public void bindData(List<SportsDetail> detailList) {
    mDetailList = detailList;
    notifyDataSetChanged();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(NewsApplication.mInstance.mInflater.inflate(R.layout.item_sports, null));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    SportsDetail item = mDetailList.get(position);
    Glide.with(mContext).load(item.picUrl).into(holder.mImage);
    holder.mTitle.setText(item.newsTitle);
    holder.mSubTitle.setText(item.newsTime);
  }

  @Override public int getItemCount() {
    if (mDetailList == null) {
      return 0;
    }
    return mDetailList.size();
  }

  static class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_image) ImageView mImage;
    @BindView(R.id.tv_title) TextView mTitle;
    @BindView(R.id.tv_subtitle) TextView mSubTitle;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
