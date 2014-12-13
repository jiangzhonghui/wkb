package com.apj.wkb.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.apj.wkb.entity.CourseDetailItem;
import com.apj.wkb.utils.DataUtils;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by student on 2014/12/13.
 */
public class LoadDetailTask extends AsyncTask<Void,Void,String> {

    Context context;
    String contentId;
    IDetailListener listener;
    public LoadDetailTask(Context context,String id,IDetailListener listener) {
        this.context=context;
        this.contentId=id;
        this.listener=listener;
    }

    @Override
    protected String doInBackground(Void... params) {
        String jsonString = "";
        try{
            jsonString  =  HttpRequest.get(String.format(DataUtils.URL_DETAIL, contentId)).accept("application/json").body();
        }catch (Exception ex){
            Log.e("DataUtils", "loadDate", ex);
        }
        return jsonString;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String jsonString) {
        Gson gson = new Gson();
        try {
            CourseDetailItem data = gson.fromJson(jsonString, new TypeToken<CourseDetailItem>(){}.getType());
            listener.onPostExecute(data);
        }catch (Exception ex){
            listener.onPostExecute(null);
        }


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
