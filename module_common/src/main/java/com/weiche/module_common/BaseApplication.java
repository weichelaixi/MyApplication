package com.weiche.module_common;

import android.app.Application;

/**
 * Created by ${chewei} on 2018/7/9.
 * params:2018/7/9
 */

public class BaseApplication extends Application {
    public static BaseApplication mDemoApp;

    public static BaseApplication getInstance(){
        if(mDemoApp == null){
            mDemoApp = new BaseApplication();
        }
        return mDemoApp;
    }

}
