package com.apj.ite.wkb.utils;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.apj.ite.wkb.AAFragment;
import com.apj.ite.wkb.BBFragment;
import com.apj.ite.wkb.CCFragment;

/**
 * Created by student on 2014/11/8.
 */
public class FragmentManagerUtil {
    private static final String ARG_SECTION_NUMBER = "section_number";
    public static Fragment getFragmentInstance(int sectionNumber){
        if(sectionNumber == 0){
            AAFragment fragment = new AAFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER,sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }else if(sectionNumber == 1){
            BBFragment fragment = new BBFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER,sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }else{
            CCFragment fragment = new CCFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER,sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
    }
}
