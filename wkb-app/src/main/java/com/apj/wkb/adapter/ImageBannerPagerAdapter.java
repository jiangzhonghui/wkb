package com.apj.wkb.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.apj.wkb.entity.CourserItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by student on 2014/11/22.
 */
public class ImageBannerPagerAdapter extends PagerAdapter {
    private Activity context;
    private List<CourserItem> topBannerData;
    private boolean isDownloadImageIn3G;
    int w =0 ;//500
    int h =0 ;//220

    public ImageBannerPagerAdapter(Activity context, List<CourserItem> imageList) {
        this.context = context;
        this.topBannerData = imageList;
    }

    @Override
    public int getCount() {
        return topBannerData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        CourserItem entity = topBannerData.get(position);
        //    ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(w, ViewGroup.LayoutParams.WRAP_CONTENT);
        //NetworkImageView imageView = new NetworkImageView(context);
        final ImageView imageView = new ImageView(context);
        String imgUrl = entity.getContentUrl();
        imageView.setTag(imgUrl);
        Picasso.with(context).load(imgUrl).fit().into(imageView);//.resize( w,h)
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view.equals(o);
    }
}
