package com.apj.wkb.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.apj.wkb.HomeActivity;

/**
 * Created by student on 2014/11/8.
 */
public class FragmentManagerUtils {

    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HomeActivity.PlaceholderFragment newInstance(int sectionNumber) {
        HomeActivity.PlaceholderFragment fragment = new HomeActivity.PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
}
