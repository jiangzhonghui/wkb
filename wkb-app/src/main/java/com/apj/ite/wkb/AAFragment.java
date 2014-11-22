package com.apj.ite.wkb;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.apj.ite.wkb.entity.*;
import java.util.*;
import com.apj.ite.wkb.adapter.ImageBannerPagerAdapter;
import com.apj.ite.wkb.entity.CourserItem;
import com.apj.ite.wkb.loader.HomeCategoryLoader;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.apj.ite.wkb.adapter.HomeAdapter;



public class AAFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Entity_aa>>{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RelativeLayout gllery_container;
    private RelativeLayout empty_view_for_you;
    private ViewPager mViewPager;
    private ProgressBar recommend_loading;
    private TextView recommend_no_data;
    private GridView grid_view_for_you;
    private TextView no_data_text_for_you;
    private ProgressBar  for_you_loading;
    private List<CourserItem> topData;
    private ImageBannerPagerAdapter topAdapter;
    private List<CourserItem> recommenData;
    private HomeAdapter recommendAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    // TODO: Rename and change types and number of parameters
    public static AAFragment newInstance(String param1, String param2) {
        AAFragment fragment = new AAFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public AAFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mViewPager = (ViewPager)this.getView().findViewById(R.id.gallery);
        gllery_container=(RelativeLayout)this.getView().findViewById(R.id.gllery_container);
        recommend_loading = (ProgressBar)this.getView().findViewById(R.id.recommend_loading);
        recommend_no_data = (TextView)this.getView().findViewById(R.id.recommend_no_data);
        grid_view_for_you = (GridView)this.getView().findViewById(R.id.grid_view_for_you);
        no_data_text_for_you = (TextView)this.getView().findViewById(R.id.no_data_text_for_you);
        for_you_loading = (ProgressBar)this.getView().findViewById(R.id.for_you_loading);
        empty_view_for_you =(RelativeLayout)this.getView().findViewById(R.id.empty_view_for_you);

        topData = new ArrayList<CourserItem>();
        topAdapter = new ImageBannerPagerAdapter(this.getActivity(),topData);

        recommenData = new ArrayList<CourserItem>();
        recommendAdapter = new HomeAdapter();

        getLoaderManager().initLoader(0,null,this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aa, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
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

    @Override
    public android.support.v4.content.Loader<List<Entity_aa>> onCreateLoader(int i, Bundle bundle) {
        return new HomeCategoryLoader(this.getActivity());
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<List<Entity_aa>> listLoader, List<Entity_aa> homeCategories) {
        if(homeCategories!=null && homeCategories.size()>0){
            gllery_container.setVisibility(View.VISIBLE);
            recommend_loading.setVisibility(View.GONE);
            grid_view_for_you.setVisibility(View.VISIBLE);
            empty_view_for_you.setVisibility(View.GONE);
            for(Entity_aa item:homeCategories){
                if(item.getType().equals("0")){
                    topData.clear();
                    topData.addAll(item.getVos());
                    topAdapter.notifyDataSetChanged();
                }
            }
        }else{
            recommend_loading.setVisibility(View.GONE);
            recommend_no_data.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<List<Entity_aa>> listLoader) {

    }

}
