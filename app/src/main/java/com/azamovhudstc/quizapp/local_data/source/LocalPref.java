package com.azamovhudstc.quizapp.local_data.source;

import android.content.Context;
import android.content.SharedPreferences;

import com.azamovhudstc.quizapp.app.App;

public class LocalPref {
    private static LocalPref getInstance;
    private static SharedPreferences appRef;
    public static final String SAVE_COIN = "coin";
    public static final String SAVE_POSITION = "position";

    private LocalPref() {

    }

    public static LocalPref getInstance() {
        if (appRef != null) {
            appRef = App.cnt.getSharedPreferences("shared", Context.MODE_PRIVATE);
            getInstance = new LocalPref();
        }
        return getInstance;
    }

    public void setSaveCoin(int coin) {
        appRef.edit().putInt(SAVE_COIN, coin);
        appRef.edit().apply();
    }

    public int getSaveCoin() {
        return appRef.getInt(SAVE_COIN, 0);
    }

    public void setSavePosition(int position) {
        appRef.edit().putInt(SAVE_POSITION, position);
    }

    public int getPosition() {
        return appRef.getInt(SAVE_POSITION, 0);
    }


}
