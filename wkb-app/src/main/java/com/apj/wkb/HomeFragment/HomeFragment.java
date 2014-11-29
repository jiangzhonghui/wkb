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
import com.apj.wkb.adapter.HomeAdapter;
import com.apj.wkb.adapter.ImageBannerPagerAdapter;
import com.apj.wkb.entity.CourserItem;
import com.apj.wkb.entity.HomeCategory;
import com.apj.wkb.loader.HomeCategoryLoader;
import com.apj.wkb.view.ScrollGridView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<HomeCategory>>{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RelativeLayout gllery_container;
    private RelativeLayout empty_view_for_you;
    private RelativeLayout recommend_empty_view;
    private ViewPager mViewPager;
    private ProgressBar recommend_loading;
    private ScrollGridView grid_view_for_you;
    private TextView recommend_no_data;
    private TextView no_data_text_for_you;
    private ProgressBar  for_you_loading;
    private List<CourserItem> topData;
    private List<CourserItem> recommenData;
    private List<CourserItem> recommendDataV1;
    private String mParam1;
    private String mParam2;
    private ImageBannerPagerAdapter topAdapter;
    private HomeAdapter recommendAdapter;
    private OnFragmentInteractionListener mListener;

    private TextView title_v1;
    private ScrollGridView grid_view_v1;
    private HomeAdapter recommendV1Adapter;

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
        //grid_view_for_you = (GridView)this.getView().findViewById(R.id.grid_view_for_you);
        recommend_empty_view =(RelativeLayout)this.getView().findViewById(R.id.recommend_empty_view);
        title_v1  = (TextView)this.getView().findViewById(R.id.title_v1);
        grid_view_for_you = (ScrollGridView)this.getView().findViewById(R.id.grid_view_for_you);
        grid_view_v1 = (ScrollGridView)this.getView().findViewById(R.id.grid_view_v1);
        no_data_text_for_you = (TextView)this.getView().findViewById(R.id.no_data_text_for_you);
        for_you_loading = (ProgressBar)this.getView().findViewById(R.id.for_you_loading);
        empty_view_for_you =(RelativeLayout)this.getView().findViewById(R.id.empty_view_for_you);
        gllery_container.setVisibility(View.GONE);
        recommend_loading.setVisibility(View.VISIBLE);
        grid_view_for_you.setVisibility(View.GONE);
        empty_view_for_you.setVisibility(View.GONE);
        grid_view_v1.setVisibility(View.GONE);

        topData = new ArrayList<CourserItem>();
        topAdapter = new ImageBannerPagerAdapter(this.getActivity(),topData);
        mViewPager.setAdapter(topAdapter);

        recommenData = new ArrayList<CourserItem>();
        recommendAdapter = new HomeAdapter(this.getActivity(),this.recommenData);
        this.grid_view_for_you.setAdapter(recommendAdapter);

        recommendDataV1  = new ArrayList<CourserItem>();
        recommendV1Adapter = new HomeAdapter(this.getActivity(),this.recommendDataV1);
        this.grid_view_v1.setAdapter(recommendV1Adapter);
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
            recommend_empty_view.setVisibility(View.GONE);
            recommenData.clear();
            topData.clear();
            recommendDataV1.clear();
            for(HomeCategory item:homeCategories){
                if(item.getType().equals("0")){
                    topData.addAll(item.getVos());
                }else if(item.getType().equals("1")) {
                    recommendDataV1.addAll(item.getVos());
                    title_v1.setText(item.getName());
                }else{
                    recommenData.addAll(item.getVos());
                }
            }
            topAdapter.notifyDataSetChanged();
            recommendAdapter.notifyDataSetChanged();
            recommendV1Adapter.notifyDataSetChanged();

            grid_view_v1.setVisibility(View.VISIBLE);
            gllery_container.setVisibility(View.VISIBLE);
            grid_view_for_you.setVisibility(View.VISIBLE);
        }else{
            recommend_loading.setVisibility(View.GONE);
            recommend_no_data.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<List<HomeCategory>> listLoader) {

    }
}
