package com.weiche.module_common.http;

import android.text.TextUtils;

import com.weiche.module_common.http.bean.BaseResponse;
import com.weiche.module_common.http.bean.Notification;
import com.weiche.module_common.http.net.ApiException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ${chewei} on 2018/7/9.
 * params:2018/7/9
 */

public class RxUtil {
    /*
     *线程统一处理
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerHelper(){
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 不是所有接口都遵循以下规则，所以各种恶心。。。。。
     * notification code  不统一 先这样，遇到再说
     */
    public static <T> Observable.Transformer<BaseResponse<T>, T> handleResult() {   //compose判断结果
        return new Observable.Transformer<BaseResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseResponse<T>> httpResponseObservable) {
                return httpResponseObservable.flatMap(new Func1<BaseResponse<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseResponse<T> response) {
                        if (response.getNotification() == null) {
                            response.setNotification(new Notification("0", "succeed"));
                        }
                        if (!"10001".equals(response.getNotification().getNotifCode())) {
                            try {
                                return createData(response.getResponseData());
                            } catch (Exception e) {
                                return Observable.error(new ApiException("服务器返回error"));
                            }
                        } else if ("用户长时间未操作或已过期,请重新登录".equals(response.getNotification().getNotifInfo())
                                || "1000".equals(response.getNotification().getNotifCode())) {
//                            exit();
                            return Observable.error(new ApiException(response.getNotification().getNotifInfo()));
                        } else if (!("0".equals(response.getNotification().getNotifCode())) && !TextUtils.isEmpty(response.getNotification().getNotifInfo())) {
                            return Observable.error(new ApiException(response.getNotification().getNotifInfo()));
                        } else {
                            return Observable.error(new ApiException("服务器返回error"));
                        }
                    }
                });
            }
        };
    }

    public static <T> Observable<T> createData(final T t){
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                }catch (Exception e){
                    subscriber.onError(e);
                }
            }
        });
    }

}
