package com.chewei.module_mvp.base;

/**
 * Created by ${chewei} on 2018/11/6.
 * 描述：视图基类
 */

public interface IBaseView<T> {

    /**
     * @descriptoin  请求前加载progress
     * @author   chewei
     * @date 2017/2/16 11:00
     */
    void showProgress();

    /**
     * @descriptoin  请求结束之后隐藏progress
     * @author   chewei
     * @date 2017/2/16 11:01
     */
    void disimissProgress();

    /**
     * @descriptoin  请求数据成功
     * @author   chewei
     * @param tData 数据类型
     * @date 2017/2/16 11:01
     */
    void loadDataSuccess(T tData);

    /**
     * @descriptoin  请求数据错误
     * @author   chewei
     * @param throwable 异常类型
     * @date 2017/2/16 11:01
     */
    void loadDataError(Throwable throwable);

}
