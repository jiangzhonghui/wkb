package com.apj.andy_app.weike.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.apj.andy_app.weike.Fragment.CategroyFragment;
import com.apj.andy_app.weike.Fragment.HomeFragment;
import com.apj.andy_app.weike.Fragment.SettingFragment;

/**
 * Created by student on 2014/11/8.
 */

public class FragmentManger {
    private static final String ARG_SECTION_NUMBER = "section_number";
    public static Fragment GetFragmentInstance(int sectionNumber) {
        if (sectionNumber==1) {
            HomeFragment fragment = new HomeFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        else if (sectionNumber==2){
           CategroyFragment fragment = new CategroyFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        else {
            SettingFragment fragment = new SettingFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
    }
}
