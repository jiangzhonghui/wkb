package com.apj.ite.wkb.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.apj.ite.wkb.entity.Entity_aa;
import com.apj.ite.wkb.utils.*;

import java.util.List;

/**
 * Created by student on 2014/11/22.
 */
public class HomeCategoryLoader extends AsyncTaskLoader<List<Entity_aa>> {

    private Context context;

    public HomeCategoryLoader(Context context) {
        super(context);
        this.context= context;
    }

    @Override
    public void deliverResult(List<Entity_aa> data) {
        super.deliverResult(data);
    }

    @Override
    public List<Entity_aa> loadInBackground() {
        return DataUtils.loadDate(context);
    }
}
