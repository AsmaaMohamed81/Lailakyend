package com.Alatheer.Projects.lailaky.Activites;

import android.app.Application;
import android.content.Context;


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

        String lang = Paper.book().read("language");
        if (lang==null)
        {
            Paper.book().write("language", Locale.getDefault().getLanguage());

        }else
        {
            Paper.book().write("language",lang);

        }
    }
}
