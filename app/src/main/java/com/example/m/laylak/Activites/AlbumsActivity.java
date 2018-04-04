package com.example.m.laylak.Activites;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.m.laylak.ApiServices.Preferences;
import com.example.m.laylak.R;

public class AlbumsActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton albumgeded,albumaty;
    private Preferences preferences;
    private String user_id;
    private ImageView logout,share;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        preferences = new Preferences(this);
        initView();
        getDataFromIntent();
        CreateAlertDialog();

    }

    private void CreateAlertDialog() {
        builder = new AlertDialog.Builder(this);
        builder.setMessage("هل ترغب في تسجيل الخروج؟");
        builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LogOut();
            }
        });
        builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AlertDialog dialog = builder.create();
                dialog.dismiss();
            }
        });
        builder.create();
    }

    private void initView() {
        albumgeded= findViewById(R.id.albumgeded);
        albumaty  = findViewById(R.id.albumaty);
        logout    = findViewById(R.id.logout);
        share     = findViewById(R.id.share);

        albumgeded.setOnClickListener(this);
        albumaty.setOnClickListener(this);
        logout.setOnClickListener(this);
        share.setOnClickListener(this);






    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent!=null)
        {
            if (intent.hasExtra("user_id"))
            {
                user_id = intent.getStringExtra("user_id");
                Log.e("getDataFromIntent: ",user_id);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.albumgeded:

                Intent i = new Intent(AlbumsActivity.this,OfferAlbum.class);
                startActivity(i);

                break;
            case R.id.albumaty :

                Intent i2 = new Intent(AlbumsActivity.this, Albumaty.class);
                startActivity(i2);
                break;

            case R.id.logout:
                builder.show();
                break;
            case R.id.share:
                break;
        }
    }

    private void LogOut() {
        preferences.clear();
        Intent intent = new Intent(AlbumsActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
