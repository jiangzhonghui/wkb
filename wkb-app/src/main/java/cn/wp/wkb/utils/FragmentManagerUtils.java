package cn.wp.wkb.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import cn.wp.wkb.fragment.CategoryFragment;
import cn.wp.wkb.fragment.HomeFragment;
import cn.wp.wkb.fragment.SettingFragment;

/**
 * Created by 伟平 on 2014/11/11.
 */
public class FragmentManagerUtils {
    public static final  String ARG_SECTION_NUMBER="section_number";
    public static Fragment getFragmentInstance(int sectionNumber) {
        if(sectionNumber==1) {
            HomeFragment fragment = new HomeFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }else if(sectionNumber==2) {
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