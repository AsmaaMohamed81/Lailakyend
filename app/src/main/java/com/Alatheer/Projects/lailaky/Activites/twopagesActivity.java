package com.Alatheer.Projects.lailaky.Activites;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.Alatheer.Projects.lailaky.Fragments.Fragment_twopages;
import com.Alatheer.Projects.lailaky.R;

public class twopagesActivity extends AppCompatActivity {

    FrameLayout frame;
    private String user_id = "", offer_id = "", paper_id = "";
    private int album_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twopages);

        getDataFromIntent();
        initView();


    }

    private void initView() {
        getSupportFragmentManager().beginTransaction().add(R.id.frame, Fragment_twopages.getInstance(user_id, offer_id, paper_id, album_size)).commit();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            album_size = Integer.parseInt(intent.getStringExtra("album_size"));
            user_id = intent.getStringExtra("user_id");
            offer_id = intent.getStringExtra("id_offer");
            paper_id = intent.getStringExtra("paper_id");


            Log.e("twopage", album_size + "");
            Log.e("twopage", user_id + "");
            Log.e("twopage", offer_id + "");

        }
    }

    @Override
    public void onBackPressed() {


        finish();
    }

}
