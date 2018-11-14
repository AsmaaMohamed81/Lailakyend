package com.Alatheer.Projects.lailaky.Activites;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.Alatheer.Projects.lailaky.ApiServices.Preferences;
import com.Alatheer.Projects.lailaky.R;

import java.util.Locale;

import io.paperdb.Paper;

public class ChooseLanguage_Activity extends AppCompatActivity {

    Button btn_ar, btn_en, continu;
    String lang;
    Preferences preferences;
    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        String lang = Paper.book().read("language");
        super.attachBaseContext(LanguageHelper.onAttach(newBase,lang));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language_);
        initView();
    }

    private void initView() {

        preferences=new Preferences(this);

        btn_ar = findViewById(R.id.btn_ar);
        btn_en = findViewById(R.id.btn_en);
        continu = findViewById(R.id.btn_continue);

        lang = Paper.book().read("language",Locale.getDefault().getLanguage());
        if (lang.equals("en")) {
            btn_en.setBackgroundResource(R.drawable.btn_en_use);
            btn_en.setTextColor(ContextCompat.getColor(ChooseLanguage_Activity.this, R.color.white));
            btn_ar.setBackgroundResource(R.drawable.btn_ar_unuse);
            btn_ar.setTextColor(ContextCompat.getColor(ChooseLanguage_Activity.this, R.color.colorPrimary));


        } else {
            btn_ar.setBackgroundResource(R.drawable.btn_ar_use);
            btn_ar.setTextColor(ContextCompat.getColor(ChooseLanguage_Activity.this, R.color.white));
            btn_en.setBackgroundResource(R.drawable.btn_en_unuse);
            btn_en.setTextColor(ContextCompat.getColor(ChooseLanguage_Activity.this, R.color.colorPrimary));
        }

        btn_ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Paper.book().write("language", "ar");
                LanguageHelper.setLocality("ar",ChooseLanguage_Activity.this);

                refreshLayout();



            }
        });

        btn_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Paper.book().write("language", "en");
                LanguageHelper.setLocality("en",ChooseLanguage_Activity.this);
                refreshLayout();



            }
        });

        continu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                preferences.setlanguage(ChooseLanguage_Activity.this,true);
                Intent intent=new Intent(ChooseLanguage_Activity.this,LoginActivity.class);
                startActivity(intent);
                finish();



            }
        });
    }

    private void refreshLayout()
    {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
