package com.apj.wkb;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
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
import com.apj.wkb.task.IDataListener;
import com.apj.wkb.utils.DataUtils;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;


public class VideoPlayerActivity extends ActionBarActivity implements IDataListener {

    private String url;
    private String title;
    private ActionBar actionBar;
    public CourseDetailItem mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
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
                    .add(R.id.container, fragment)
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

    @Override
    public void postData(List<HomeCategory> data) {

    }

    @Override
    public void postDetailData(CourseDetailItem data) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements IDataListener {

        private VideoView videoView;
        private String url;
        private String title;

        public PlaceholderFragment() {
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            if (getArguments() != null) {
                url = getArguments().getString("url");
                title = getArguments().getString("title");
                LoadDetailTask task =new LoadDetailTask(this.getActivity(),url,this);
                task.execute();
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_video_player, container, false);
            videoView = (VideoView)rootView.findViewById(R.id.videoView);
            return rootView;
        }

        @Override
        public void postData(List<HomeCategory> data) {

        }

        @Override
        public void postDetailData(CourseDetailItem data) {
            String mp4Url = data.getVideoList().get(0).getRepovideourlmp4();
            MediaController mc = new MediaController(this.getActivity());
            videoView.setMediaController(mc);
            videoView.setVideoURI(Uri.parse(
                    mp4Url));
            videoView.requestFocus();
            videoView.start();
        }

        public class LoadDetailTask extends AsyncTask<Void,Void,String>{

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
                CourseDetailItem data = gson.fromJson(jsonString, new TypeToken<CourseDetailItem>(){}.getType());
                listener.postDetailData(data);

            }
        }
    }
}
