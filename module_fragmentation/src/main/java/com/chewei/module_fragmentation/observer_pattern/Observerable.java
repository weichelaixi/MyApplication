package com.chewei.module_fragmentation.observer_pattern;

/**
 * Created by ${chewei} on 2018/6/28.
 * params:2018/6/28
 * 抽象被观察者
 * 需要设置添加，删除，通知被观察功能
 */

public interface Observerable {
    void registerObser(Observer observer);
    void deleteObserver(Observer observer);
    void notifyObserver();
}
