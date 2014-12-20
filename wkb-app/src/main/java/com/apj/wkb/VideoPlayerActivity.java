package com.apj.wkb;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.MediaController;
import android.widget.VideoView;

import com.apj.wkb.entity.CourseDetailItem;
import com.apj.wkb.entity.HomeCategory;
import com.apj.wkb.fragment.PlayerFragment;
import com.apj.wkb.task.IDataListener;
import com.apj.wkb.utils.DataUtils;
import com.apj.wkb.utils.FragmentManagerUtils;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viewpagerindicator.TabPageIndicator;

import java.util.List;
import java.util.Locale;


public class VideoPlayerActivity extends ActionBarActivity {

    private String url;
    private String title;
    private ActionBar actionBar;
    public CourseDetailItem mData;
    TabPageIndicator tabs;
    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_video_player);
        setContentView(R.layout.activity_vdetail);
        actionBar= this.getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);

        url = this.getIntent().getStringExtra("url");
        title = this.getIntent().getStringExtra("title");
        actionBar.setTitle(title);

        tabs = (TabPageIndicator)this.findViewById(R.id.v_detail_tabs);
        mViewPager = (ViewPager)this.findViewById(R.id.v_detail_pager);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabs.setViewPager(mViewPager);

        if (savedInstanceState == null) {

            PlayerFragment fragment = new PlayerFragment();
            Bundle args = new Bundle();
            args.putString("url", url);
            args.putString("title", title);
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.v_detail_player, fragment)
                    .commit();
        }
    }

    public void setVideoDetail(CourseDetailItem data){
        this.mData = data;
        this.actionBar.setTitle(data.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.video_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return FragmentManagerUtils.getDetailActivityFragmentInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.vdetail_tab_title_info).toUpperCase(l);
                case 1:
                    return getString(R.string.vdetail_tab_title_info).toUpperCase(l);
                case 2:
                    return getString(R.string.vdetail_tab_title_info).toUpperCase(l);
                case 3:
                    return getString(R.string.vdetail_tab_title_info).toUpperCase(l);
            }
            return null;
        }
    }


}
