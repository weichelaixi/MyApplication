package com.chewei.module_mvp.model;

import com.chewei.module_mvp.base.BaseModel;
import com.chewei.module_mvp.base.IBaseRequestCallBack;
import com.chewei.module_mvp.bean.WeatherInfoBean;

/**
 * Created by ${chewei} on 2018/11/6.
 * 描述:
 */

public class WeatherModelImp extends BaseModel implements WeatherModel<WeatherInfoBean>{
    @Override
    public void loadWeather(String city, String key, IBaseRequestCallBack<WeatherInfoBean> iBaseRequestCallBack) {

    }

    @Override
    public void onUnsubscribe() {

    }
}
