package com.apj.wkb.task;

import android.content.Context;
import android.os.AsyncTask;

import com.apj.wkb.entity.HomeCategory;
import com.apj.wkb.utils.DataUtils;
import com.handmark.pulltorefresh.library.internal.Utils;

import java.util.List;

/**
 * Created by student on 2014/12/7.
 */
public class LoadDataTask extends AsyncTask<String, String, List<HomeCategory>> {

    Context context;
    String str;
    ILoadData iLoadData;

    public LoadDataTask(Context context, String str, ILoadData iLoadData) {
        this.context = context;
        this.str = str;
        this.iLoadData = iLoadData;
    }

    @Override
    protected List<HomeCategory> doInBackground(String... params) {
        List<HomeCategory> data = DataUtils.loadDate(context);
        return data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<HomeCategory> homeCategories) {
        iLoadData.loadData(homeCategories);
        //super.onPostExecute(homeCategories);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(List<HomeCategory> homeCategories) {
        super.onCancelled(homeCategories);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
