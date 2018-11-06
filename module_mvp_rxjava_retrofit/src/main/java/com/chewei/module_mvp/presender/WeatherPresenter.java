package com.chewei.module_mvp.presender;

/**
 * Created by ${chewei} on 2018/11/6.
 * 描述:MVP中的P接口
 */

public interface WeatherPresenter {
    /**
     * @descriptoin 请求天气数据
     * @author  chewei
     * @param key key
     * @param city 城市
     * @return
     */
    void loadWeather(String key, String city);

    /**
     * @descriptoin 注销subscribe
     * @author  chewei
     */
    void unSubscribe();
}
