package com.apj.wkb.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by student on 2014/11/22.
 */
public class TestLoader extends AsyncTaskLoader {

    public TestLoader(Context context) {
        super(context);
    }

    @Override
    public Object loadInBackground() {
        return null;
    }
}
