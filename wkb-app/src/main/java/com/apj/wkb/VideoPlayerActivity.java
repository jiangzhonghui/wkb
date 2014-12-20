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
import com.apj.wkb.task.OnVdFragmentInteractionListener;
import com.apj.wkb.utils.DataUtils;
import com.apj.wkb.utils.FragmentManagerUtils;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;
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

        tabs = (TabPageIndicator)findViewById(R.id.v_detail_tabs);
        mViewPager = (ViewPager)findViewById(R.id.v_detail_pager);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabs.setViewPager(mViewPager);

        if (savedInstanceState == null) {

            LoadDetailTask task =new LoadDetailTask(this,url,new IDataListener() {
                @Override
                public void postData(List<HomeCategory> data) {

                }

                @Override
                public void postDetailData(CourseDetailItem data) {
                    mData = data;

                    PlayerFragment fragment = new PlayerFragment();
                    Bundle args = new Bundle();
                    args.putString("url", url);
                    args.putString("title", title);
                    fragment.setArguments(args);

                    pagerData.add("简介");
                    pagerData.add("目录");
                    pagerData.add("相关课程");
                    pagerData.add("跟帖");

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
    public void onFragmentInteraction(CourseDetailItem data) {
        this.mData= data;
    }

    @Override
    public CourseDetailItem onFragmentInteraction() {
        return mData;
    }


    private List<String> pagerData =new ArrayList<String>();

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
            return pagerData.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_fragment_video_brief).toUpperCase(l);
                case 1:
                    return getString(R.string.title_fragment_video_list).toUpperCase(l);
                case 2:
                    return getString(R.string.title_fragment_video_recommends).toUpperCase(l);
                case 3:
                    return getString(R.string.title_fragment_video_comments).toUpperCase(l);
            }
            return null;
        }

    }
    public class LoadDetailTask extends AsyncTask<Void,Void,String> {

            private String contentId;
            private Context context;
            private IDataListener listener;

            public LoadDetailTask(Context context,String id,IDataListener listener) {
                super();
                contentId = id;
                this.context =context;
                this.listener = listener;
            }

            @Override
            protected String doInBackground(Void... params) {
                String jsonString = "";
                try{
                    jsonString  =  HttpRequest.get(String.format(DataUtils.URL_DETAIL, contentId)).accept("application/json").body();
                }catch (Exception ex){
                    Log.e("DataUtils", "loadDate", ex);
                }
                return jsonString;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String jsonString) {
                Gson gson = new Gson();
                try{
                    CourseDetailItem data = gson.fromJson(jsonString, new TypeToken<CourseDetailItem>(){}.getType());
                    listener.postDetailData(data);
                }catch (Exception ex){
                    listener.postDetailData(null);
                }
            }
        }

}
