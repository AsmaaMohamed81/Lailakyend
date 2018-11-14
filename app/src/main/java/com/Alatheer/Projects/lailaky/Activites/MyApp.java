package com.Alatheer.Projects.lailaky.Activites;

import android.app.Application;
import android.content.Context;
import android.util.Log;


import java.util.Locale;

import io.paperdb.Paper;

public class MyApp extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LanguageHelper.onAttach(base,"ar"));

    }


    @Override
    public void onCreate() {
        super.onCreate();
        Paper.init(this);
        String lang = Paper.book().read("language",Locale.getDefault().getLanguage());
        if (lang==null)
        {
            Paper.book().write("language","ar");
        }else
        {
            Paper.book().write("language","en");

        }

    }
}
