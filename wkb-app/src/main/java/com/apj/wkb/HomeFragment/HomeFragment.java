package com.apj.wkb.HomeFragment;

import android.app.Activity;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apj.wkb.OnFragmentInteractionListener;
import com.apj.wkb.R;
import com.apj.wkb.adapter.ImageBannerPagerAdapter;
import com.apj.wkb.entity.CourserItem;
import com.apj.wkb.entity.HomeCategory;
import com.apj.wkb.loader.HomeCategoryLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<HomeCategory>>{
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
    private String mParam1;
    private String mParam2;
    private ImageBannerPagerAdapter topAdapter;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance(String param1, String param2) {
        Fragment fragment = new Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public HomeFragment() {
        // Required empty public constructor
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
        return inflater.inflate(R.layout.frag_hm_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewPager = (ViewPager)this.getView().findViewById(R.id.gallery);
        gllery_container=(RelativeLayout)this.getView().findViewById(R.id.gllery_container);
        recommend_loading = (ProgressBar)this.getView().findViewById(R.id.recommend_loading);
        recommend_no_data = (TextView)this.getView().findViewById(R.id.recommend_no_data);
        grid_view_for_you = (GridView)this.getView().findViewById(R.id.grid_view_for_you);
        no_data_text_for_you = (TextView)this.getView().findViewById(R.id.no_data_text_for_you);
        for_you_loading = (ProgressBar)this.getView().findViewById(R.id.for_you_loading);
        empty_view_for_you =(RelativeLayout)this.getView().findViewById(R.id.empty_view_for_you);
        gllery_container.setVisibility(View.GONE);
        recommend_loading.setVisibility(View.VISIBLE);
        grid_view_for_you.setVisibility(View.GONE);
        empty_view_for_you.setVisibility(View.GONE);

        topData = new ArrayList<CourserItem>();
        topAdapter = new ImageBannerPagerAdapter(this.getActivity(),topData);
        mViewPager.setAdapter(topAdapter);

        getLoaderManager().initLoader(0,null,this);
    }

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
    public android.support.v4.content.Loader<List<HomeCategory>> onCreateLoader(int i, Bundle bundle) {
        return new HomeCategoryLoader(this.getActivity());
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<List<HomeCategory>> listLoader, List<HomeCategory> homeCategories) {
        if(homeCategories!=null && homeCategories.size()>0){
            gllery_container.setVisibility(View.VISIBLE);
            recommend_loading.setVisibility(View.GONE);
            grid_view_for_you.setVisibility(View.VISIBLE);
            empty_view_for_you.setVisibility(View.GONE);
            for(HomeCategory item:homeCategories){
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
    public void onLoaderReset(android.support.v4.content.Loader<List<HomeCategory>> listLoader) {

    }
}
