package com.apj.wkb.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.apj.wkb.R;
import com.apj.wkb.entity.CourserItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by student on 2014/11/22.
 */
public class ImageBannerPagerAdapter  extends PagerAdapter {

    private Activity context;
    private List<CourserItem> topBannerData;
    private boolean isDownloadImageIn3G;
    int w =0 ;//500
    int h =0 ;//220
    DisplayImageOptions options;
    public ImageBannerPagerAdapter(Activity context, List<CourserItem> imageList) {
        this.context = context;
        this.topBannerData = imageList;

        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

    }

    public void addData(List<CourserItem> data){
        topBannerData.clear();
        topBannerData.addAll(data);
    }

    @Override
    public int getCount() {
        return topBannerData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        CourserItem entity = topBannerData.get(position);
        View view = context.getLayoutInflater().inflate(R.layout.top_image_layout,null);
        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        String imgUrl = entity.getPicUrl();
        imageView.setTag(imgUrl);
        ImageLoader.getInstance().displayImage(imgUrl, imageView, options);
        //Picasso.with(context).load(imgUrl).fit().into(imageView);//.resize( w,h)
        ((ViewPager) container).addView(view, 0);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}


