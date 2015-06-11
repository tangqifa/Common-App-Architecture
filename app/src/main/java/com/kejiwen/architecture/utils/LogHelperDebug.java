package com.kejiwen.architecture.utils;

import android.util.Log;

import com.kejiwen.architecture.constant.FeatureConfig;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LogHelperDebug {


    public static final String TAG = "com.kejiwen.architecture";

    public static void d(String subTag, String msg) {
        if (FeatureConfig.DEBUG_LOG)
            Log.d(TAG, getLogMsg(subTag, msg));
    }

    public static void i(String subTag, String msg) {
        if (FeatureConfig.DEBUG_LOG)
            Log.i(TAG, getLogMsg(subTag, msg));
    }

    public static void w(String subTag, String msg) {
        if (FeatureConfig.DEBUG_LOG)
            Log.w(TAG, getLogMsg(subTag, msg));
    }

    public static void w(String subTag, String msg, Throwable e) {
        if (FeatureConfig.DEBUG_LOG)
            Log.w(TAG, getLogMsg(subTag, msg + " Exception: " + getExceptionMsg(e)));
    }

    public static void e(String subTag, String msg) {
        if (FeatureConfig.DEBUG_LOG)
            Log.e(TAG, getLogMsg(subTag, msg));
    }

    public static void e(String subTag, String msg, Throwable e) {
        if (FeatureConfig.DEBUG_LOG)
            Log.e(TAG, getLogMsg(subTag, msg + " Exception: " + getExceptionMsg(e)));
    }

    private static String getLogMsg(String subTag, String msg) {
        return "[" + subTag + "] " + msg;
    }

    protected static String getExceptionMsg(Throwable e) {
        StringWriter sw = new StringWriter(1024);
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        pw.close();
        return sw.toString();
    }

}
