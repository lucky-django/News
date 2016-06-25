package com.fury.news.network;

import android.content.Context;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.fury.news.NewsApplication;
import java.io.InputStream;

/**
 * Created by lucky-django on 16/6/25.
 */
public class OkGlideModule implements GlideModule {

  @Override public void applyOptions(Context context, GlideBuilder builder) {

  }

  @Override public void registerComponents(Context context, Glide glide) {
    OkHttpUrlLoader.Factory factory =
        new OkHttpUrlLoader.Factory(NewsApplication.mInstance.mOkHttpClient);
    glide.register(GlideUrl.class, InputStream.class, factory);
  }
}
