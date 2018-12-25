package com.chewei.module_mvp.toolView.rotate_viewpager;

import java.io.Serializable;

/**
 * Created by ${chewei} on 2017/9/28.
 * params:2017/9/28
 * 活动轮播图
 */

public class ActivityImageBean implements Serializable {

    /**
     * id : 1
     * name : aaa
     */

    private long id;
    private String name;
    private String typeName;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
