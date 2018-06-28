package com.chewei.module_fragmentation.observer_pattern;

import com.weiche.module_common.utils.KeepLog;

/**
 * Created by ${chewei} on 2018/6/28.
 * params:2018/6/28
 */

public class ObserverUser implements Observer {
    public String name;
    public String getMsg;

    public ObserverUser(String name){
        this.name = name;
    }

    @Override
    public void update(String msg) {
        this.getMsg = msg;
        readMsg();
    }

    public void readMsg(){
        KeepLog.e(KeepLog.TAG,name+"作为观察者收到了消息：：："+getMsg);
    }

}
