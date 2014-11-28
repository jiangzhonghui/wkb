package com.apj.wkb.loader;

import android.content.Context;
import android.content.Loader;
import android.support.v4.content.AsyncTaskLoader;


import com.apj.wkb.entity.CourserItem;
import com.apj.wkb.entity.HomeCategory;
import com.apj.wkb.utils.DataUtils;

import java.util.List;
/**
 * Created by Liuyu on 2014/11/28.
 */
public class HomeCategoryLoader extends  AsyncTaskLoader<List<HomeCategory>>{

    private Context context;
    public HomeCategoryLoader(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void deliverResult(List<HomeCategory> data) {
        super.deliverResult(data);
    }

    @Override
    public List<HomeCategory> loadInBackground() {
        return DataUtils.loadDate(context);
    }
}
