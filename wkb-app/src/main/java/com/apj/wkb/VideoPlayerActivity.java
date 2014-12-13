package com.apj.wkb;

import android.app.FragmentManager;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.MediaController;
import android.widget.VideoView;

import com.apj.wkb.HomeFragment.PlaceholderFragment;
import com.apj.wkb.entity.CourseDetailItem;
import com.apj.wkb.task.IDetailListener;
import com.apj.wkb.task.LoadDetailTask;


public class VideoPlayerActivity extends ActionBarActivity {

    private String url;
    private String title;
    private ActionBar actionBar;
    public CourseDetailItem mData;

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

        if (savedInstanceState == null) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putString("url", url);
            args.putString("title", title);
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.v_detail_player, fragment)
                    .commit();
        }
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
}