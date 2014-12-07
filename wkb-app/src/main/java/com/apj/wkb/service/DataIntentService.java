package com.apj.wkb.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import com.apj.wkb.entity.CourserItem;
import com.apj.wkb.entity.HomeCategory;
import com.apj.wkb.provider.contentprovider.ProviderUtils;
import com.apj.wkb.utils.DataUtils;

import java.util.List;
import java.util.logging.LogRecord;

/**
 * Created by timo on 14/12/6.
 */
public class DataIntentService extends IntentService{


    public DataIntentService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        List<HomeCategory> items = DataUtils.loadDate(this);
        ProviderUtils utils = new ProviderUtils(this);
        for(HomeCategory category:items){
            for(CourserItem item:category.getVos()){
                utils.addCourseItem(item);
            }
        }
    }
}
