package com.chewei.module_mvp.presender;

import android.content.Context;

import com.chewei.module_mvp.base.BasePresenterImp;
import com.chewei.module_mvp.bean.WeatherInfoBean;
import com.chewei.module_mvp.model.WeatherModelImp;
import com.chewei.module_mvp.view.WeatherView;

/**
 * Created by ${chewei} on 2018/11/7.
 * 描述:
 */

public class WeatherPresenterImp extends BasePresenterImp<WeatherView, WeatherInfoBean> implements WeatherPresenter {

    Context mContext;
    WeatherModelImp weatherModelImp;

    public WeatherPresenterImp(WeatherView weatherView, Context context) {
        super(weatherView);
        weatherModelImp = new WeatherModelImp(context);
        this.mContext = context;
    }

    @Override
    public void loadWeather(String key, String city) {
        weatherModelImp.loadWeather(key,city,this);
    }

    @Override
    public void unSubscribe() {
        weatherModelImp.onUnsubscribe();
    }
}
