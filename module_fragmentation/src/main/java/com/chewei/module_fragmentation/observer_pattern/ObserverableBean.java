package com.chewei.module_fragmentation.observer_pattern;

import com.weiche.module_common.utils.KeepLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${chewei} on 2018/6/28.
 * params:2018/6/28
 */

public class ObserverableBean implements Observerable {
    public List<Observer> observers;
    public String msg;

    public ObserverableBean(){
        observers = new ArrayList<>();
    }

    @Override
    public void registerObser(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        if(observers != null){
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObserver() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(msg);
        }
    }

    public void setInformation(String message){
        this.msg = message;
        KeepLog.e(KeepLog.TAG,message);
        notifyObserver();
    }

}
