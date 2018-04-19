package com.zk.expandablelistviews;

import java.util.List;

/**
 * Created by ${zk} on 2018/4/19 0019.
 * 欢迎每一天
 */

public class TitleInfo {
    private String            title;
    private List<ContentInfo> info;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ContentInfo> getInfo() {
        return info;
    }

    public void setInfo(List<ContentInfo> info) {
        this.info = info;
    }
}
