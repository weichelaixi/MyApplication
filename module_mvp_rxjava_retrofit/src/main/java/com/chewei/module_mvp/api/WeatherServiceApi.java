package com.chewei.module_mvp.api;

import com.chewei.module_mvp.bean.WeatherInfoBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ${chewei} on 2018/11/6.
 * 描述:
 */

public interface WeatherServiceApi {

    //请求的接口地址：http://api.avatardata.cn/Weather/Query?key=75bfe88f27a34311a41591291b7191ce&cityname=%E9%95%BF%E6%B2%99
    @GET("Weather/Query?")
    Observable<WeatherInfoBean> loadWeatherInfo(@Query("key") String key, @Query("cityname") String cityname);
}
