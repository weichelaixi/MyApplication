package com.chewei.cw.myapplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.weiche.module_common.BaseApplication;

/**
 * Created by ${chewei} on 2018/6/13.
 */
public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            //一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }
}
