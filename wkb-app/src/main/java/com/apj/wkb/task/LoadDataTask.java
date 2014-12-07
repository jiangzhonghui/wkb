package com.apj.wkb.task;

import android.content.Context;
import android.os.AsyncTask;

import com.apj.wkb.HomeFragment.HomeFragment;
import com.apj.wkb.entity.HomeCategory;
import com.apj.wkb.utils.DataUtils;

import java.util.List;

/**
 * Created by student on 2014/12/7.
 */
public class LoadDataTask extends AsyncTask<Void,Void,List<HomeCategory>> {
    Context context;
    String parms;
    IDataListener listener;
    public LoadDataTask(Context context,String parms,IDataListener listener) {
        this.context=context;
        this.parms=parms;
        this.listener=listener;
    }

    @Override
    protected List<HomeCategory> doInBackground(Void... params) {
        List<HomeCategory> list= DataUtils.loadDate(context);
        return list;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<HomeCategory> list) {
       listener.onPostExecute(list);
       // super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
