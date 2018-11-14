package com.Alatheer.Projects.lailaky.Activites;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Locale;

public class LanguageHelper {
    private static final String LANGUAGE="SELECTED_LANGUAGE";
    public static Context onAttach(Context context,String language)
    {
        String lang = getLanguage(context,language);
        return setLocality(lang,context);
    }

    public static Context setLocality(String lang,Context context) {
        saveLanguage(lang,context);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
        {
            return updateResource(lang,context);
        }
        return updateLegacy(lang,context);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResource(String lang, Context context) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);
        return context.createConfigurationContext(configuration);
    }

    @SuppressWarnings("deprecation")
    private static Context updateLegacy(String lang, Context context) {
        Locale locale = new Locale(lang);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale=locale;
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1)
        {
            configuration.setLayoutDirection(locale);
        }

        resources.updateConfiguration(configuration,resources.getDisplayMetrics());
        return context;
    }

    private static void saveLanguage(String lang,Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(LANGUAGE,lang);
        edit.apply();
    }

    private static String getLanguage(Context context,String default_language)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String lang = preferences.getString(LANGUAGE,default_language);
        return lang;
    }


}