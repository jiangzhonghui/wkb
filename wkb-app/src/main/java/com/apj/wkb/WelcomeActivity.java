package com.apj.wkb;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by student on 2014/11/15.
 */
public class WelcomeActivity extends Activity {

    private ViewPager guide_page;
    private LayoutInflater mInflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_welcome);
        this.guide_page= (ViewPager)this.findViewById(R.id.guide_page);
        this.mInflater = this.getLayoutInflater();
        this.guide_page.setAdapter(new WelcomePagerAdapter());
        new Handler().postDelayed(new Runnable() {
            public void run() {
                guide_page.setVisibility(View.VISIBLE);
            }
        }, 500);
    }

    private class WelcomePagerAdapter extends PagerAdapter{

        public WelcomePagerAdapter() {
            super();
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mInflater.inflate(R.layout.browse_pics, null);
            ImageView mImg = (ImageView) view.findViewById(R.id.browse_pics_img);
            if(position==0){
                mImg.setImageResource(R.drawable.guide_0);
            }else if(position==1){
                mImg.setImageResource(R.drawable.guide_1);
            }else if(position==2){
                mImg.setImageResource(R.drawable.guide_2);
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return false;
        }
    }
}
