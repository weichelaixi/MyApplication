package com.chewei.module_mvp.model;

import android.content.Context;

import com.chewei.module_mvp.api.WeatherServiceApi;
import com.chewei.module_mvp.base.BaseModel;
import com.chewei.module_mvp.base.IBaseRequestCallBack;
import com.chewei.module_mvp.bean.WeatherInfoBean;
import com.chewei.module_mvp.helper.RxUtils;

import rx.Subscriber;

/**
 * Created by ${chewei} on 2018/11/6.
 * 描述:
 */

public class WeatherModelImp extends BaseModel implements WeatherModel<WeatherInfoBean>{

    Context mContext;
    public WeatherModelImp(Context context) {
        this.mContext = context;
    }

    @Override
    public void loadWeather(String city, String key, final IBaseRequestCallBack<WeatherInfoBean> iBaseRequestCallBack) {
        retrofitManager.getService().loadWeatherInfo(key,city)
                .compose(RxUtils.<WeatherInfoBean>rxSchedulerHelper())
                .subscribe(new Subscriber<WeatherInfoBean>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        //onStart它总是在 subscribe 所发生的线程被调用 ,如果你的subscribe不是主线程，则会出错，则需要指定线程;
                        iBaseRequestCallBack.beforeRequest();
                    }

                    @Override
                    public void onCompleted() {
                        //回调接口：请求已完成，可以隐藏progress
                        iBaseRequestCallBack.requestComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //回调接口：请求异常
                        iBaseRequestCallBack.requestError(e);
                    }

                    @Override
                    public void onNext(WeatherInfoBean weatherInfoBean) {
                        //回调接口：请求成功，获取实体类对象
                        iBaseRequestCallBack.requestSuccess(weatherInfoBean);
                    }
                });
    }

    @Override
    public void onUnsubscribe() {

    }
}
