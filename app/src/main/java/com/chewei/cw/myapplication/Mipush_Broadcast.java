package com.chewei.cw.myapplication;

import android.content.Context;

import com.weiche.module_common.utils.KeepLog;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

/**
 * Created by ${chewei} on 2018/7/22.
 */
public class Mipush_Broadcast extends PushMessageReceiver {

    //透传消息到达客户端时调用
    //作用：可通过参数message从而获得透传消息，具体请看官方SDK文档
    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage message) {

        //打印消息方便测试
        KeepLog.e(KeepLog.TAG,"透传消息到达了");
        KeepLog.e(KeepLog.TAG,("透传消息是"+message.toString()));

    }


//通知消息到达客户端时调用
    //注：应用在前台时不弹出通知的通知消息到达客户端时也会回调函数
    //作用：通过参数message从而获得通知消息，具体请看官方SDK文档

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage message) {
        //打印消息方便测试
        KeepLog.e(KeepLog.TAG,"通知消息到达了");
        KeepLog.e(KeepLog.TAG,"通知消息是"+message.toString());
    }

    //用户手动点击通知栏消息时调用
    //注：应用在前台时不弹出通知的通知消息到达客户端时也会回调函数
    //作用：1. 通过参数message从而获得通知消息，具体请看官方SDK文档
    //2. 设置用户点击消息后打开应用 or 网页 or 其他页面

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage message) {

        //打印消息方便测试
        KeepLog.e(KeepLog.TAG,"用户点击了通知消息");
        KeepLog.e(KeepLog.TAG,"通知消息是" + message.toString());
        KeepLog.e(KeepLog.TAG,"点击后,会进入应用" );

    }

    //用来接收客户端向服务器发送命令后的响应结果。
    @Override
    public void onCommandResult(Context context, MiPushCommandMessage message) {

        String command = message.getCommand();
        System.out.println(command );


        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {

                //打印信息便于测试注册成功与否
                KeepLog.e(KeepLog.TAG,"注册成功");

            } else {
                KeepLog.e(KeepLog.TAG,"注册失败");
            }
        }
    }

    //用于接收客户端向服务器发送注册命令后的响应结果。
    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {

        String command = message.getCommand();
        System.out.println(command );

        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {

                //打印日志：注册成功
                KeepLog.e(KeepLog.TAG,"注册成功");
            } else {
                //打印日志：注册失败
                KeepLog.e(KeepLog.TAG,"注册失败");
            }
        } else {
            KeepLog.e(KeepLog.TAG,"其他情况"+message.getReason());
        }
    }


}
