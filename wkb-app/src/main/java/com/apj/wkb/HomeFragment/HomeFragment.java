package com.apj.wkb.HomeFragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;

/*
*
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment#newInstance} factory method to
 * create an instanc this fragment.
 *
*/
public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<HomeCategory>> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // ViewPager
    private ViewPager gallery = null;
    private RelativeLayout recommend_empty_view = null;
    private TextView recommend_no_data = null;
    private ProgressBar recommend_loading = null;

    private List<CourserItem> mViewPageData = null;
    private ImageBannerPagerAdapter mViewPageAdapter = null;

    // Guess you like
    private GridView grid_view_for_you = null;
    private RelativeLayout empty_view_for_you = null;
    private TextView no_data_text_for_you = null;
    private ProgressBar for_you_loading = null;

    private List<CourserItem> mGuessLikeData = null;
    private HomeAdapter mGuessLikeAdapter = null;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // ViewPager
        gallery = (ViewPager)this.getActivity().findViewById(R.id.gallery);
        recommend_empty_view = (RelativeLayout)this.getActivity().findViewById(R.id.recommend_empty_view);
        recommend_no_data = (TextView)this.getActivity().findViewById(R.id.recommend_no_data);
        recommend_loading = (ProgressBar)this.getActivity().findViewById(R.id.recommend_loading);

        mViewPageData = new ArrayList<CourserItem>();
        mViewPageAdapter = new ImageBannerPagerAdapter(this.getActivity(), mViewPageData);
        gallery.setAdapter(mViewPageAdapter);

        // Guess you like
        grid_view_for_you = (GridView)this.getActivity().findViewById(R.id.grid_view_for_you);
        empty_view_for_you = (RelativeLayout)this.getActivity().findViewById(R.id.empty_view_for_you);
        no_data_text_for_you = (TextView)this.getActivity().findViewById(R.id.no_data_text_for_you);
        for_you_loading = (ProgressBar)this.getActivity().findViewById(R.id.for_you_loading);

        mGuessLikeData = new ArrayList<CourserItem>();
        mGuessLikeAdapter = new HomeAdapter(this.getActivity(), mGuessLikeData);
        grid_view_for_you.setAdapter(mGuessLikeAdapter);

        Log.d("InitElement", "ok");
        getLoaderManager().initLoader(0, null, this);
    }

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
    public Loader<List<HomeCategory>> onCreateLoader(int i, Bundle bundle) {
        Log.d("Init_HomeCategoryLoader", "ok");
        return new HomeCategoryLoader(this.getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<HomeCategory>> listLoader, List<HomeCategory> homeCategories) {
        Log.d("Init_HandlerData", homeCategories.size() + "");

        // bind data
        for(HomeCategory homeCategory:homeCategories){
            // ViewPager
            if(homeCategory.getType().equals("0") && homeCategory.getVos().size()>0){
                mViewPageData.clear();
                mViewPageData.addAll(homeCategory.getVos());
                mViewPageAdapter.notifyDataSetChanged();

                recommend_empty_view.setVisibility(View.GONE);
                gallery.setVisibility(View.GONE);
                recommend_empty_view.setVisibility(View.GONE);
            }else{
                recommend_empty_view.setVisibility(View.VISIBLE);
                recommend_loading.setVisibility(View.GONE);
            }

            // Guess you like
            if(homeCategory.getType().equals("0") && homeCategory.getVos().size()>0){
                mGuessLikeData.clear();
                mGuessLikeData.addAll( homeCategory.getVos());
                mGuessLikeAdapter.notifyDataSetChanged();

                grid_view_for_you.setVisibility(View.VISIBLE);
                empty_view_for_you.setVisibility(View.GONE);
            }else{
                grid_view_for_you.setVisibility(View.GONE);
                empty_view_for_you.setVisibility(View.VISIBLE);
                for_you_loading.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public void onLoaderReset(Loader<List<HomeCategory>> listLoader) {

    }
}
