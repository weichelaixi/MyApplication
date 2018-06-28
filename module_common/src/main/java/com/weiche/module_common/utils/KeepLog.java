package com.weiche.module_common.utils;

import android.util.Log;

import com.weiche.module_common.BuildConfig;

/**
 * Created by ${chewei} on 2018/6/27.
 * params:2018/6/27
 */

public class KeepLog {

    public static final String TAG = "TAG";

    public static void e(String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg, tr);
        }

    }
}
