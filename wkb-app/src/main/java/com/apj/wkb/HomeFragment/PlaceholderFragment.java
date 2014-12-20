package com.apj.wkb.HomeFragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.apj.wkb.R;
import com.apj.wkb.VideoPlayerActivity;
import com.apj.wkb.entity.CourseDetailItem;
import com.apj.wkb.task.IDetailListener;
import com.apj.wkb.task.LoadDetailTask;
import com.apj.wkb.task.OnVdFragmentInteractionListener;

/**
 * Created by student on 2014/12/13.
 */
public class PlaceholderFragment extends Fragment implements IDetailListener {
    private VideoView videoView;
    private String url;
    private String title;

    private OnVdFragmentInteractionListener mListener;

    private CourseDetailItem mData;

    public PlaceholderFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString("url");
            title = getArguments().getString("title");
//            LoadDetailTask task =new LoadDetailTask(this.getActivity(),url,this);
//            task.execute();
            onPostExecute(mData);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_player, container, false);
        videoView = (VideoView)rootView.findViewById(R.id.videoView);
        return rootView;
    }

    @Override
    public void onPostExecute(CourseDetailItem data) {
        if (data!=null) {
            VideoPlayerActivity activity = (VideoPlayerActivity)this.getActivity();
            activity.setVideoDetail(data);
            String mp4Url = data.getVideoList().get(0).getRepovideourlmp4();
            MediaController mc = new MediaController(this.getActivity());
            videoView.setMediaController(mc);
            videoView.setVideoURI(Uri.parse(
                    mp4Url));
            videoView.requestFocus();
            videoView.start();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnVdFragmentInteractionListener) activity;
            mData= mListener.onFragmentInteraction();
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
