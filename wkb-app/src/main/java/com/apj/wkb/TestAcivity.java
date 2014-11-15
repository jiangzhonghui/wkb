package com.apj.wkb;

import android.app.Activity;
import android.util.Log;

/**
 * Created by student on 2014/11/15.
 */
public class TestAcivity extends Activity {

    private static final String Tag="Tag";

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_login);
        Log.d("Tag","onStart");
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
