package com.apj.wkb.task;

import android.content.Context;
import android.hardware.Camera;
import android.os.AsyncTask;

import com.apj.wkb.entity.HomeCategory;
import com.apj.wkb.utils.DataUtils;

import java.util.List;

/**
 * Created by student on 2014/12/7.
 */
public class LoadDataTask extends AsyncTask<Void,Void, List<HomeCategory>> {
   Context context;
    String  paras;
    IDataListener listener;

    public LoadDataTask( Context context,String  paras,IDataListener listener) {
        this.context=context;
        this.paras=paras;
        this.listener=listener;
       // super();
    }

    @Override
    protected List<HomeCategory> doInBackground(Void ... Paras) {
        List<HomeCategory> data= DataUtils.loadDate(context);
        return data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<HomeCategory> homeCategories) {
        listener.onPostExecute(homeCategories);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(List<HomeCategory> homeCategories) {
     // super.onCancelled(homeCategories);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
