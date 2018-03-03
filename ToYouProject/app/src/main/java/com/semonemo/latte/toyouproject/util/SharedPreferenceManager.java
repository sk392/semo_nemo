package com.semonemo.latte.toyouproject.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by latte on 2018. 1. 17..
 */

public class SharedPreferenceManager {
    private static final String SETTING = "setting";
    public static final String USER_ID = "_user_id_";
    public static final String USER_NAME = "_user_name_";
    public static final String USER_TOKEN_ACCESS = "_user_access_";
    public static final String test = "test";
    public static final String USER_TOKEN_REFRESH = "_user_refresh_";
    public static final String USER_PROFILE = "_user_profile_";

    private static SharedPreferenceManager sharedPreferencesManager;
    private SharedPreferences pref;

    public static SharedPreferenceManager getInstance(){
        if (sharedPreferencesManager == null) {
            synchronized (SharedPreferenceManager.class) {
                if (sharedPreferencesManager == null)
                    sharedPreferencesManager = new SharedPreferenceManager();
            }
        }
        return sharedPreferencesManager;
    }

    private void getPref(Context cont) {
        if (pref == null) {
            pref = cont.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        }
    }

    public void load(Context context) {
        getPref(context);
    }
    public String getPrefStringData(String key){
        return pref.getString(key, null);
    }

    public int getPrefIntData(String key){
        return pref.getInt(key, Integer.MIN_VALUE);
    }

    public long getPrefLongData(String key){
        return pref.getLong(key, Long.MIN_VALUE);
    }

    public boolean getPrefBooleanData(String key){
        return pref.getBoolean(key, false);
    }

    public void setPrefData(String key, boolean value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void setPrefStringData(String key, String value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void setPrefIntData(String key, int value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void setPrefLongData(String key, long value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public void clear(Context context) {
        if (pref == null)
            getPref(context);
        if (pref != null)
            pref.edit().clear().commit();
    }
}
