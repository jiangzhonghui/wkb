package com.apj.wkb.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.apj.wkb.HomeFragment.CategoryFragment;
import com.apj.wkb.HomeFragment.HomeFragment;
import com.apj.wkb.HomeFragment.SettingFragment;
import com.apj.wkb.HomeFragment.VCommentsFragment;
import com.apj.wkb.HomeFragment.VCouresInfoFragment;
import com.apj.wkb.HomeFragment.VReateCouriseFragment;
import com.apj.wkb.HomeFragment.VvlistFragment;

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

    public static Fragment getDetailInstance(int sectionNumber){
        if(sectionNumber==1){
            VCouresInfoFragment fragment = new VCouresInfoFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }else if(sectionNumber==2){
            VCommentsFragment fragment = new VCommentsFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }else if(sectionNumber==3){
            VReateCouriseFragment fragment = new VReateCouriseFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }else{
            VvlistFragment fragment = new VvlistFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
    }

}
