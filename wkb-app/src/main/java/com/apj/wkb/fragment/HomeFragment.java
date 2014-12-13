package com.apj.wkb.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.apj.wkb.OnFragmentInteractionListener;
import com.apj.wkb.R;
import com.apj.wkb.adapter.HomeAdapter;
import com.apj.wkb.adapter.ImageBannerPagerAdapter;
import com.apj.wkb.entity.CourseDetailItem;
import com.apj.wkb.entity.CourserItem;
import com.apj.wkb.entity.HomeCategory;
import com.apj.wkb.loader.HomeCategoryLoader;
import com.apj.wkb.provider.contentprovider.ProviderUtils;
import com.apj.wkb.task.IDataListener;
import com.apj.wkb.task.LoadDataTask;
import com.apj.wkb.view.ScrollGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<HomeCategory>>,IDataListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RelativeLayout gllery_container;
    private RelativeLayout empty_view_for_you;
    private RelativeLayout recommend_empty_view;
    private ViewPager mViewPager;
    private ProgressBar recommend_loading;
    private TextView recommend_no_data;
    private ScrollGridView grid_view_for_you;
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

    private TextView title_v2;
    private ScrollGridView grid_view_v2;
    private HomeAdapter recommendV2Adapter;
    private List<CourserItem> recommendDataV2;

    private List<CourserItem> recommendDataV3;
    private TextView title_v3;
    private ScrollGridView grid_view_v3;
    private HomeAdapter recommendV3Adapter;

    private PullToRefreshScrollView scrollView;
    private Context mContext;
    private HomeFragment me;

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
        recommend_empty_view =(RelativeLayout)this.getView().findViewById(R.id.recommend_empty_view);

        //Scroll View
        scrollView =(PullToRefreshScrollView)this.getView().findViewById(R.id.scrollView);
        mContext = this.getActivity();
        me = this;

        grid_view_for_you = (ScrollGridView)this.getView().findViewById(R.id.grid_view_for_you);

        title_v1  = (TextView)this.getView().findViewById(R.id.title_v1);
        grid_view_v1 = (ScrollGridView)this.getView().findViewById(R.id.grid_view_v1);

        title_v2  = (TextView)this.getView().findViewById(R.id.title_v2);
        grid_view_v2 = (ScrollGridView)this.getView().findViewById(R.id.grid_view_v2);

        title_v3  = (TextView)this.getView().findViewById(R.id.title_v3);
        grid_view_v3 = (ScrollGridView)this.getView().findViewById(R.id.grid_view_v3);

        no_data_text_for_you = (TextView)this.getView().findViewById(R.id.no_data_text_for_you);
        for_you_loading = (ProgressBar)this.getView().findViewById(R.id.for_you_loading);
        empty_view_for_you =(RelativeLayout)this.getView().findViewById(R.id.empty_view_for_you);
        gllery_container.setVisibility(View.GONE);
        recommend_loading.setVisibility(View.VISIBLE);
        grid_view_for_you.setVisibility(View.GONE);
        empty_view_for_you.setVisibility(View.GONE);
        grid_view_v1.setVisibility(View.GONE);
        title_v1.setVisibility(View.GONE);

        grid_view_v2.setVisibility(View.GONE);
        title_v2.setVisibility(View.GONE);

        grid_view_v3.setVisibility(View.GONE);
        title_v3.setVisibility(View.GONE);

        topData = new ArrayList<CourserItem>();
        topAdapter = new ImageBannerPagerAdapter(this.getActivity(),topData);
        mViewPager.setAdapter(topAdapter);
        mViewPager.setOnPageChangeListener(onPageChangeListener);

        recommenData = new ArrayList<CourserItem>();
        recommendAdapter = new HomeAdapter(this.getActivity(),this.recommenData);
        this.grid_view_for_you.setAdapter(recommendAdapter);

        recommendDataV1  = new ArrayList<CourserItem>();
        recommendV1Adapter = new HomeAdapter(this.getActivity(),this.recommendDataV1);
        this.grid_view_v1.setAdapter(recommendV1Adapter);

        recommendDataV2  = new ArrayList<CourserItem>();
        recommendV2Adapter = new HomeAdapter(this.getActivity(),this.recommendDataV2);
        this.grid_view_v2.setAdapter(recommendV2Adapter);

        recommendDataV3  = new ArrayList<CourserItem>();
        recommendV3Adapter = new HomeAdapter(this.getActivity(),this.recommendDataV3);
        this.grid_view_v3.setAdapter(recommendV3Adapter);

        getLoaderManager().initLoader(0,null,this);

        scrollView.setOnRefreshListener(onRefreshListener);

    }

    PullToRefreshBase.OnRefreshListener onRefreshListener=new PullToRefreshBase.OnRefreshListener() {
        @Override
        public void onRefresh(PullToRefreshBase refreshView) {
            LoadDataTask task = new LoadDataTask(mContext,"",me);
            task.execute();
        }
    };

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i2) {

        }

        @Override
        public void onPageSelected(int i) {

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_hm_main, container, false);
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
            bindDataToUI(homeCategories);
        }else{
            recommend_loading.setVisibility(View.GONE);
            recommend_no_data.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<List<HomeCategory>> listLoader) {

    }

    @Override
    public void postData(List<HomeCategory> homeCategories) {

        if(homeCategories!=null && homeCategories.size()>0) {

            scrollView.onRefreshComplete();
            bindDataToUI(homeCategories);

            ProviderUtils utils = new ProviderUtils(this.getActivity());
            utils.removeCourseItem();

            for(HomeCategory category:homeCategories){
                for(CourserItem item:category.getVos()){
                    item.setTypeName(category.getName());
                    utils.addCourseItem(item);
                }
            }
            //
        }
    }

    @Override
    public void postDetailData(CourseDetailItem data) {

    }

    private void bindDataToUI(List<HomeCategory> homeCategories){

        gllery_container.setVisibility(View.VISIBLE);
        recommend_loading.setVisibility(View.GONE);
        grid_view_for_you.setVisibility(View.VISIBLE);
        empty_view_for_you.setVisibility(View.GONE);
        recommend_empty_view.setVisibility(View.GONE);

        recommenData.clear();
        topData.clear();
        recommendDataV1.clear();
        recommendDataV2.clear();
        recommendDataV3.clear();

        for(HomeCategory item:homeCategories){
            if(item.getType().equals("0")){
                topData.addAll(item.getVos());
            }else if(item.getType().equals("1")) {
                recommendDataV1.addAll(item.getVos());
                title_v1.setText(item.getName());
            }else if(item.getType().equals("2")) {
                recommendDataV2.addAll(item.getVos());
                title_v2.setText(item.getName());
            }else if(item.getType().equals("3")) {
                recommendDataV3.addAll(item.getVos());
                title_v3.setText(item.getName());
            }else if(item.getType().equals("4")) {
                recommenData.addAll(item.getVos());
            }

        }
        topAdapter.notifyDataSetChanged();
        recommendAdapter.notifyDataSetChanged();
        recommendV1Adapter.notifyDataSetChanged();
        recommendV2Adapter.notifyDataSetChanged();
        recommendV3Adapter.notifyDataSetChanged();


        title_v1.setVisibility(View.VISIBLE);
        title_v2.setVisibility(View.VISIBLE);
        title_v3.setVisibility(View.VISIBLE);
        grid_view_v1.setVisibility(View.VISIBLE);
        grid_view_v2.setVisibility(View.VISIBLE);
        grid_view_v3.setVisibility(View.VISIBLE);
        gllery_container.setVisibility(View.VISIBLE);
        grid_view_for_you.setVisibility(View.VISIBLE);
    }
}