package com.weiche.module_common.http.bean;

/**
 * Created by ${chewei} on 2018/7/9.
 * params:2018/7/9
 * 结果基本每个接口返回值都不一样
 */

public class BaseResponse<T> {
    public Notification notification;
    public T responseData;

    public BaseResponse() {
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public T getResponseData() {
        return responseData;
    }
}
