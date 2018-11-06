package com.chewei.module_mvp.base;

import com.chewei.module_mvp.helper.RetrofitManager;

/**
 * Created by ${chewei} on 2018/11/6.
 * 描述:业务对象的基类
 */

public class BaseModel {
    //retrofit请求数据的管理类
    public RetrofitManager retrofitManager;

    public BaseModel() {
        retrofitManager = RetrofitManager.builder();
    }
}
