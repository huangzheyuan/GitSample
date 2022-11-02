package com.example.smartcity.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.smartcity.bean.PersonBean;

public class PrefStore {

    private static final String STORE_NAME = "Settings";
    private static Context mContext = null;
    private static PrefStore instance = null;

    public static PrefStore getInstance(Context context) {
        if (instance == null) {
            instance = new PrefStore(context);
        }
        return instance;
    }

    public void updateUser(PersonBean personBean){

       savePref("usernickname", personBean.getNickName());
       savePref("avater", personBean.getAvater());
       savePref("phonenumber", personBean.getPhonenumber());
       savePref("sex", personBean.getSex());
       savePref("username", personBean.getUserName());
       savePref("idCard", personBean.getIdCard());
       savePref("userId", personBean.getUserId());

    }

    public PrefStore(Context context) {
        mContext = context.getApplicationContext();
    }

    public boolean savePref(String key, String value) {

        if (mContext != null) {
            SharedPreferences pref = mContext.getSharedPreferences(STORE_NAME,
                    Context.MODE_PRIVATE);
            Editor editor = pref.edit();
            editor.putString(key, value);
            editor.commit();
            return true;
        } else {
            return false;
        }

    }
    public boolean savePref(String key, float value) {

        if (mContext != null) {
            SharedPreferences pref = mContext.getSharedPreferences(STORE_NAME,
                    Context.MODE_PRIVATE);
            Editor editor = pref.edit();
            editor.putFloat(key, value);
            editor.commit();
            return true;
        } else {
            return false;
        }

    }
    public boolean savePref(String key, int value) {

        if (mContext != null) {
            SharedPreferences pref = mContext.getSharedPreferences(STORE_NAME,
                    Context.MODE_PRIVATE);
            Editor editor = pref.edit();
            editor.putInt(key, value);
            editor.commit();
            return true;
        } else {
            return false;
        }

    }

    public String getPref(String key, String defValue) {

        if (mContext != null) {
            SharedPreferences pref = mContext.getSharedPreferences(STORE_NAME,
                    Context.MODE_PRIVATE);
            return pref.getString(key, defValue);
        } else {
            return null;
        }

    }

    public boolean removePref(String key) {
        if (mContext != null) {
            SharedPreferences pref = mContext.getSharedPreferences(STORE_NAME,
                    Context.MODE_PRIVATE);
            Editor editor = pref.edit();
            editor.remove(key);
            editor.commit();
            return true;
        } else {
            return false;
        }
    }

    public boolean clearPref() {
        if (mContext != null) {
            SharedPreferences pref = mContext.getSharedPreferences(STORE_NAME,
                    Context.MODE_MULTI_PROCESS);
            Editor editor = pref.edit();
            editor.clear();
            editor.commit();
            return true;
        } else {
            return false;
        }
    }

}
