package com.thud.thpt_dh.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by khanh on 5/11/2016.
 */
public class Preference {
    private SharedPreferences preferences;

    public Preference(Context mContext){
        preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    }
}
