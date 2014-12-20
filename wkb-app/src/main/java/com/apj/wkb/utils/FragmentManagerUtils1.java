package com.apj.wkb.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.apj.wkb.fragment.frag.IntroductionFragment;
import com.apj.wkb.fragment.frag.RelatedToRecommendFragment;
import com.apj.wkb.fragment.frag.commentsFragment;
import com.apj.wkb.fragment.frag.directoryFragment;

/**
 * Created by student on 2014/12/20.
 */
public class FragmentManagerUtils1 {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static Fragment getFragmentInstance(int sectionNumber){
        if(sectionNumber==1){
            directoryFragment fragment = new directoryFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }else if(sectionNumber==2){
            IntroductionFragment fragment = new IntroductionFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }else if(sectionNumber==3){
            RelatedToRecommendFragment fragment = new RelatedToRecommendFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }else{
            commentsFragment fragment = new commentsFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
    }
}
