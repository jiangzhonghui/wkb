package com.apj.wkb.task;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;

import com.apj.wkb.entity.HomeCategory;
import com.apj.wkb.utils.DataUtils;

import java.util.List;

/**
 * Created by student on 2014/12/7.
 */
public class LoadDataTask extends AsyncTask<Void,Void,List<HomeCategory>> {

    String params;
    Context context;
    IDataListener dataListener;

    public LoadDataTask(Context context,String params,IDataListener dataListener){
        this.context = context;
        this.params = params;
        this.dataListener = dataListener;
    }

    @Override
    protected List<HomeCategory> doInBackground(Void... params) {
        List<HomeCategory> data=  DataUtils.loadDate(context);
        return data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<HomeCategory> data) {
        dataListener.postData(data);
        //super.onPostExecute(data);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(List<HomeCategory> aVoid) {
        super.onCancelled(aVoid);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
