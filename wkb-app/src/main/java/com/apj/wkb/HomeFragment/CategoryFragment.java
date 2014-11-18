package com.apj.wkb.HomeFragment;

import android.app.Activity;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.GridView;

import android.widget.SimpleAdapter;


import com.apj.wkb.OnFragmentInteractionListener;
import com.apj.wkb.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 * Use the {@link com.apj.wkb.HomeFragment.CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class CategoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private View view;
    private GridView gridView;
    private  SimpleAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final  Resources resources=getResources();
        final String[] tag= resources.getStringArray(R.array.all_course_filter_tag);

        List<Map<String,Object>> listItems=new ArrayList<Map<String, Object>>();
        for(int i=0;i<tag.length;i++){
            Map<String ,Object> list=new HashMap<String, Object>();
            list.put("text",tag[i]);
            listItems.add(list);
        }

        adapter = new SimpleAdapter(
                getActivity() ,listItems, R.layout.cell
                ,new String[]{"text"},new int[]{R.id.buttom1});

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment_category, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridView=(GridView)view.findViewById(R.id.gridtag);
        gridView.setAdapter(adapter);
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



}
