package com.chewei.module_mvp.base;

/**
 * Created by ${chewei} on 2018/11/6.
 * 描述：代理对象的基础实现 ：  一些基础的方法
 * @param <V> 视图接口对象(view) 具体业务各自继承自IBaseView
 * @param <T> 业务请求返回的具体对象
 */

public class BasePresenterImp<V extends IBaseView , T> implements IBaseRequestCallBack {

    private IBaseView iBaseView = null;

    public BasePresenterImp(V v){
        iBaseView = v;
    }

    /**
     * @descriptoin 请求之前显示progress
     * @author  chewei
     */
    @Override
    public void beforeRequest() {
        iBaseView.showProgress();
    }

    /**
    * @descriptoin 请求异常显示异常信息
    * @author  chewei
    * @param throwable 异常信息
    * */
    @Override
    public void requestError(Throwable throwable) {
        iBaseView.loadDataError(throwable);
        iBaseView.disimissProgress();
    }
    /**
     * @descriptoin 请求完成之后隐藏progress
     * @author  chewei
     */
    @Override
    public void requestComplete() {
        iBaseView.disimissProgress();
    }

    /**
     * @descriptoin 请求成功获取成功之后的数据信息
     * @author  chewei
     * @param callBack 回调的数据
     */
    @Override
    public void requestSuccess(Object callBack) {
        iBaseView.loadDataSuccess(callBack);
    }
}
