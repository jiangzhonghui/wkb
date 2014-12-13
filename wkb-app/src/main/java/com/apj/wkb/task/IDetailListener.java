package com.apj.wkb.task;

import com.apj.wkb.entity.CourseDetailItem;

import java.util.List;

/**
 * Created by student on 2014/12/13.
 */
public interface IDetailListener {
    public void onPostExecute(CourseDetailItem list);
}
