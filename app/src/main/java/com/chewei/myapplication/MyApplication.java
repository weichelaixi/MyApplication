package com.chewei.myapplication;

import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.weiche.module_common.BaseApplication;
import com.weiche.module_common.utils.KeepLog;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

/**
 * Created by ${chewei} on 2018/6/13.
 */
public class MyApplication extends BaseApplication {

    public String TAG = "com.chewei.myapplication";
    //小米消息推送APP_ID ，APP_KEY
    private static final String APP_ID = "2882303761517841168";
    private static final String APP_KEY = "5491784111168";
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            //一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
        KeepLog.e(KeepLog.TAG,"PUSH----------------------------------");
        if(shouldInit()){
            KeepLog.e(KeepLog.TAG,"PUSH+++++++++++++++++++++++++++++++++++");
//            PushManager.getPushManager().inintPush(this);
            MiPushClient.registerPush(this, APP_ID, APP_KEY);
        }
        //打开Log
        LoggerInterface newLogger = new LoggerInterface() {

            @Override
            public void setTag(String tag) {
                // ignore
            }

            @Override
            public void log(String content, Throwable t) {
                Log.d(TAG, content, t);
            }

            @Override
            public void log(String content) {
                Log.d(TAG, content);
            }
        };
        Logger.setLogger(this, newLogger);
    }
}
