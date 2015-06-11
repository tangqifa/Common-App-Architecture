package com.kejiwen.architecture.utils;

import android.content.Context;
import android.preference.PreferenceManager;

public class SharePreferenceUtils {

    public static void setLongPref(Context context, String key,
            Long value) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putLong(key, value).commit();
    }

    public static long getLongPref(Context context, String key,
            long defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(
                context).getLong(key, defaultValue);
    }

    public static void setIntPref(Context context, String key,
            int value) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putInt(key, value).commit();
    }

    public static int getIntPref(Context context, String key,
            int defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(
                context).getInt(key, defaultValue);
    }

    public static void setStringPref(Context context, String key,
            String value) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putString(key, value).commit();
    }

    public static String getStringPref(Context context, String key,
            String defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(
                context).getString(key, defaultValue);
    }

    public static boolean getBooleanPref(Context context,
            String key, boolean defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(
                context).getBoolean(key, defaultValue);
    }

    public static void setBooleanPref(Context context, String key,
            boolean value) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putBoolean(key, value).commit();
    }

    public static void removePref(Context context, String key) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().remove(key).commit();
    }

    public static void setStringSecPref(Context context, String key,
            String value) {
        SecurePreferences preferences = SecurePreferences.getInstance(context, true);
        preferences.put(key, value);
    }

    public static String getStringSecPref(Context context, String key,
            String defaultValue) {
        SecurePreferences preferences = SecurePreferences.getInstance(context, true);
        return preferences.getString(key);
    }
}
