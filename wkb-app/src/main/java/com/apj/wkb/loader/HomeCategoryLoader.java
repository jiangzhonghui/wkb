package com.apj.wkb.loader;

import android.content.Context;
import android.content.Loader;
import android.support.v4.content.AsyncTaskLoader;

import com.apj.wkb.entity.HomeCategory;
import com.apj.wkb.utils.DataUtils;

import java.util.List;

/**
 * Created by student on 2014/11/22.
 */
public class HomeCategoryLoader extends AsyncTaskLoader<List<HomeCategory>> {

    private Context context;

    public HomeCategoryLoader(Context context) {
        super(context);
        this.context= context;
    }



    @Override
    public void deliverResult(List<HomeCategory> data) {
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        //forceLoad();
        super.onStartLoading();
    }

    @Override
    public List<HomeCategory> loadInBackground() {
        return DataUtils.loadDate(context);
    }
}
