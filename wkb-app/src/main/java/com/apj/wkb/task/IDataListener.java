package com.apj.wkb.task;

import com.apj.wkb.entity.HomeCategory;

import java.util.List;

/**
 * Created by student on 2014/12/7.
 */
public interface IDataListener {
    public void onPostExecute(List<HomeCategory> list);
}
