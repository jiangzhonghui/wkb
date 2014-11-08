package com.apj.moxin.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.apj.moxin.fragment.CategoryFragment;
import com.apj.moxin.fragment.HomeFragment;
import com.apj.moxin.fragment.SettingFragment;

/**
 * Created by student on 2014/11/8.
 */


public class FragementManagerUtils {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public Fragment getFragmentInstance(int sectionNumber){
        if(sectionNumber == 1){
            HomeFragment fragment = new HomeFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        else if(sectionNumber == 2){
            CategoryFragment fragment = new CategoryFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        else{
            SettingFragment fragment = new SettingFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
    }
}
