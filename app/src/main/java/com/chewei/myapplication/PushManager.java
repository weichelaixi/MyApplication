package com.chewei.myapplication;

import android.app.Application;
import android.os.Build;

import com.weiche.module_common.utils.KeepLog;
import com.xiaomi.mipush.sdk.MiPushClient;

/**
 * Created by ${chewei} on 2018/7/22.
 */
public class PushManager {

    static PushManager pushManager;
    //小米消息推送APP_ID ，APP_KEY
    private static final String APP_ID = "2882303761517841168";
    private static final String APP_KEY = "5491784111168";

    public static PushManager getPushManager() {
        if(pushManager == null){
            pushManager = new PushManager();
        }
        return pushManager;
    }

    /*
     * 推送初始化
     */
    public void inintPush(Application context){
        String manufacturer = Build.MANUFACTURER;
        //这个字符串可以自己定义,例如判断华为就填写huawei,魅族就填写meizu
        if ("xiaomi".equalsIgnoreCase(manufacturer)) {
            //注册推送服务
            //注册成功后会向DemoMessageReceiver发送广播
            // 可以从DemoMessageReceiver的onCommandResult方法中MiPushCommandMessage对象参数中获取注册信息
            MiPushClient.registerPush(context.getApplicationContext(), APP_ID, APP_KEY);
            KeepLog.e(KeepLog.TAG,"APP_ID----------------------------------"+APP_ID);
        }
    }
}
