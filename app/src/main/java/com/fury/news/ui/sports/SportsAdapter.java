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
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.fury.news.NewsApplication;
import com.fury.news.R;
import com.fury.news.model.sports.SportsDetail;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

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

  public void addData(Collection<SportsDetail> details) {
    mDetailList.addAll(details);
    notifyDataSetChanged();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(NewsApplication.mInstance.mInflater.inflate(R.layout.item_sports, null));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    SportsDetail item = mDetailList.get(position);
    Glide.with(mContext)
        .load(item.picUrl)
        .listener(new LoggingListener<String, GlideDrawable>())
        .into(holder.mImage);
    holder.mTitle.setText(item.newsTitle);
    holder.mSubTitle.setText(mContext.getString(R.string.sub_title, item.origin, item.newsTime));
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

  public class LoggingListener<T, R> implements RequestListener<T, R> {
    @Override
    public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
      android.util.Log.d("Glide",
          String.format(Locale.ROOT, "onException(%s, %s, %s, %s)", e, model, target,
              isFirstResource), e);
      return false;
    }

    @Override public boolean onResourceReady(Object resource, Object model, Target target,
        boolean isFromMemoryCache, boolean isFirstResource) {
      android.util.Log.d("Glide",
          String.format(Locale.ROOT, "onResourceReady(%s, %s, %s, %s, %s)", resource, model, target,
              isFromMemoryCache, isFirstResource));
      return false;
    }
  }
}
