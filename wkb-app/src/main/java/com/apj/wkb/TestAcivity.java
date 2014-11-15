package com.apj.wkb;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.apj.wkb.HomeFragment.HomeFragment;
import com.apj.wkb.utils.FragmentManagerUtils;

/**
 * Created by student on 2014/11/15.
 */
public class TestAcivity extends FragmentActivity {

    private static final String Tag="Tag";

    /*@Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(new HomeFragment(),"abc");
        fragmentTransaction.commit();

    }*/

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_login);
        Log.d("Tag","onStart");
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }
}
