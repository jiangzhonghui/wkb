package com.apj.wkb;

import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.apj.wkb.HomeFragment.PlaceholderFragment;
import com.apj.wkb.entity.CourseDetailItem;
import com.apj.wkb.task.IDetailListener;
import com.apj.wkb.task.LoadDetailTask;
import com.apj.wkb.task.OnVdFragmentInteractionListener;
import com.apj.wkb.utils.FragmentManagerUtils;
import com.viewpagerindicator.TabPageIndicator;

import java.util.Locale;


public class VideoPlayerActivity extends ActionBarActivity implements OnVdFragmentInteractionListener {

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

            LoadDetailTask task =new LoadDetailTask(this,url,new IDetailListener() {
                @Override
                public void onPostExecute(CourseDetailItem data) {
                    mData = data;

                    PlaceholderFragment fragment = new PlaceholderFragment();
                    Bundle args = new Bundle();
                    args.putString("url", url);
                    args.putString("title", title);
                    fragment.setArguments(args);

                    mSectionsPagerAdapter.notifyDataSetChanged();
                    tabs.notifyDataSetChanged();

                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.v_detail_player, fragment)
                            .commit();
                }
            });
            task.execute();

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

    @Override
    public void onFragmentInteraction(CourseDetailItem data) {
        this.mData= data;
    }

    @Override
    public CourseDetailItem onFragmentInteraction() {
        return mData;
    }

    /**
     * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return FragmentManagerUtils.getDetailInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.vdetail_tab_title_info).toUpperCase(l);
                case 1:
                    return getString(R.string.vdetail_tab_title_recommends).toUpperCase(l);
                case 2:
                    return getString(R.string.vdetail_tab_title_comments).toUpperCase(l);
                case 3:
                    return getString(R.string.vdetail_tab_title_videos).toUpperCase(l);
            }
            return null;
        }
    }

}
