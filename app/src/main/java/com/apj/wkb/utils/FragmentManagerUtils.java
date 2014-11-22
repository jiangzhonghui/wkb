package com.apj.wkb.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.apj.wkb.CategoryFragment;
import com.apj.wkb.SettingFragment;
import com.apj.wkb.HomeFragment;

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

}
