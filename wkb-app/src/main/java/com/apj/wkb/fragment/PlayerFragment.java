package com.apj.wkb.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.apj.wkb.R;
import com.apj.wkb.VideoPlayerActivity;
import com.apj.wkb.entity.CourseDetailItem;
import com.apj.wkb.entity.HomeCategory;
import com.apj.wkb.task.IDataListener;
import com.apj.wkb.task.OnVdFragmentInteractionListener;
import com.apj.wkb.utils.DataUtils;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public  class PlayerFragment extends Fragment implements IDataListener {

    private VideoView videoView;
    private String url;
    private String title;
    private OnVdFragmentInteractionListener mListener;

    private CourseDetailItem mData;

    public PlayerFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString("url");
            title = getArguments().getString("title");
            postDetailData(mData);
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
    public void postData(List<HomeCategory> data) {

    }

    @Override
    public void postDetailData(CourseDetailItem data) {
        if(data!=null){
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

    // TODO: Rename method, update argument and hook method into UI event
    public CourseDetailItem getData() {
        if (mListener != null) {
            return mListener.onFragmentInteraction();
        }
        return null;
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