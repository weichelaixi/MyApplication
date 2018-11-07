package com.chewei.module_mvp.model;

import android.content.Context;

import com.chewei.module_mvp.api.WeatherServiceApi;
import com.chewei.module_mvp.base.BaseModel;
import com.chewei.module_mvp.base.IBaseRequestCallBack;
import com.chewei.module_mvp.bean.WeatherInfoBean;
import com.weiche.module_common.http.RxUtil;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ${chewei} on 2018/11/6.
 * 描述:MVP中的M实现类，处理业务逻辑数据
 */

public class WeatherModelImp extends BaseModel implements WeatherModel<WeatherInfoBean>{
    private Context context = null;
    private WeatherServiceApi weatherServiceApi;
    private CompositeSubscription mCompositeSubscription;

    public WeatherModelImp(Context mContext) {
        super();
        context = mContext;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void loadWeather(String key, String city, final IBaseRequestCallBack iBaseRequestCallBack) {
        Subscription subscription = retrofitManager.getService().loadWeatherInfo(key, city)  //将subscribe添加到subscription，用于注销subscribe
                .compose(RxUtil.<WeatherInfoBean>rxSchedulerHelper())
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
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onUnsubscribe() {
        //判断状态
        if(mCompositeSubscription.isUnsubscribed()){
            mCompositeSubscription.clear();  //注销
            mCompositeSubscription.unsubscribe();
        }
    }
}
