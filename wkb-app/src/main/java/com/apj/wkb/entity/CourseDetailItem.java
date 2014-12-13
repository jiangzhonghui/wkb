package com.apj.wkb.entity;

import java.util.List;

/**
 * Created by student on 2014/12/13.
 */
public class CourseDetailItem {
    public List<VideoListItem> getVideoListItemList() {
        return videoListItemList;
    }

    public void setVideoListItemList(List<VideoListItem> videoListItemList) {
        this.videoListItemList = videoListItemList;
    }

    private List<VideoListItem> videoListItemList;
}
