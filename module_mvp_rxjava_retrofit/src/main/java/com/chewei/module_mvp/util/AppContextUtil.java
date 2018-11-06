package com.chewei.module_mvp.util;

import android.content.Context;

/**
 * Created by ${chewei} on 2018/11/6.
 * 描述:上下文的单利对象
 */

public class AppContextUtil {

    private static Context sContext;

    private AppContextUtil() {

    }

    public static void init(Context context) {
        sContext = context;
    }

    public static Context getInstance() {
        if (sContext == null) {
            throw new NullPointerException("the context is null,please init AppContextUtil in Application first.");
        }
        return sContext;
    }
}
