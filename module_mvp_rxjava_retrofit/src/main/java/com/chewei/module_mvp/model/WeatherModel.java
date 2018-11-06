package com.chewei.module_mvp.model;

import com.chewei.module_mvp.base.IBaseRequestCallBack;

/**
 * Created by ${chewei} on 2018/11/6.
 * 描述:MVP中的M；处理获取网络天气数据
 */

public interface WeatherModel<T> {
    /**
     * @descriptoin  获取网络数据
     * @author   dc
     * @param city 城市
     *@param key key
     * @param iBaseRequestCallBack 数据的回调接口
     * @date 2017/2/17 19:01
     */
    void loadWeather(String city,String key, IBaseRequestCallBack<T> iBaseRequestCallBack);

    /**
     * @descriptoin 注销subscribe
     * @author
     * @param
     * @date 2017/2/17 19:02
     * @return
     */
    void onUnsubscribe();

}
