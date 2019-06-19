package com.Alatheer.Projects.lailaky.Activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.Alatheer.Projects.lailaky.ApiServices.Preferences;
import com.Alatheer.Projects.lailaky.R;

import io.paperdb.Paper;


public class Splash extends AppCompatActivity {

    Preferences preferences;

    LinearLayout linearLayout;
    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        String lang = Paper.book().read("language");
        Log.d("Asmaa", "attachBaseContext: "+lang);
        super.attachBaseContext(LanguageHelper.onAttach(newBase,lang));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        preferences=new Preferences(this);
//        Paper.init(this);
//        Paper.book().read("language");

        linearLayout=findViewById(R.id.image);

        Animation animation=AnimationUtils.loadAnimation(this,R.anim.fade);
        linearLayout.clearAnimation();
        linearLayout.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                int secondsDelayed = 1;
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        if (!preferences.getlanguage(Splash.this)){
                            startActivity(new Intent(Splash.this, ChooseLanguage_Activity.class));
                            finish();
                        }else {
                            startActivity(new Intent(Splash.this, LoginActivity.class));
                            finish();

                        }

                    }
                }, secondsDelayed * 3000);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }
    }

