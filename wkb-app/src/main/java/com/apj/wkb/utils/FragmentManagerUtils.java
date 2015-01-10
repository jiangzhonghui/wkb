package com.apj.wkb.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.apj.wkb.fragment.CategoryFragment;
import com.apj.wkb.fragment.HomeFragment;
import com.apj.wkb.fragment.SettingFragment;
import com.apj.wkb.fragment.VdCommentsFragment;
import com.apj.wkb.fragment.VdCourseInfoFragment;
import com.apj.wkb.fragment.VdRelatedCourseFragment;
import com.apj.wkb.fragment.VdVlistFragment;

/**
 * Created by student on 2014/11/8.
 */
public class FragmentManagerUtils {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static Fragment getFragmentInstance(int sectionNumber){
        if(sectionNumber==1){
            HomeFragment fragment = new HomeFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }else if(sectionNumber==2){
            CategoryFragment fragment = new CategoryFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }else{
            SettingFragment fragment = new SettingFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
    }

    public static Fragment getDetailActivityFragmentInstance(int sectionNumber){
        if(sectionNumber==1){
            //
            VdCourseInfoFragment fragment = new VdCourseInfoFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }else if(sectionNumber==2){
            VdVlistFragment fragment = new VdVlistFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;

        }else if(sectionNumber==3){
            VdRelatedCourseFragment fragment = new VdRelatedCourseFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
         }  else{
            VdCommentsFragment fragment = new VdCommentsFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
    }
}
