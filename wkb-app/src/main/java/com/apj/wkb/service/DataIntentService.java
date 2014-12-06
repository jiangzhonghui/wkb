package com.apj.wkb.service;

import android.app.IntentService;
import android.content.Intent;

import com.apj.wkb.entity.CourserItem;
import com.apj.wkb.entity.HomeCategory;
import com.apj.wkb.provider.contentprovider.ProviderUtils;
import com.apj.wkb.utils.DataUtils;

import java.util.List;

/**
 * Created by timo on 14/12/6.
 */
public class DataIntentService extends IntentService{

    public DataIntentService(String name) {
        super(name);
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
