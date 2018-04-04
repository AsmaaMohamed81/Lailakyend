package com.example.m.laylak.ApiServices;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Emad on 2018-04-04.
 */

public class Preferences {

    private Context context;

    public Preferences(Context context) {
        this.context = context;
    }

    public void CreatePref(String user_id)
    {
        SharedPreferences sPref = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("id",user_id);
        editor.putString("session","login");

        editor.apply();
    }

    public void clear()
    {
        SharedPreferences sPref = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("id","");
        editor.putString("session","logout");

        editor.apply();


    }
}
