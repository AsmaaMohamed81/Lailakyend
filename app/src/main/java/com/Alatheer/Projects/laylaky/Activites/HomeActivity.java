package com.Alatheer.Projects.laylaky.Activites;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.Alatheer.Projects.laylaky.ApiServices.Preferences;
import com.Alatheer.Projects.laylaky.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton albumgeded,albumaty;
    private Preferences preferences;
    private String user_id;
    private ImageView back,share;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        preferences = new Preferences(this);
        initView();
        getDataFromIntent();
        CreateAlertDialog();

    }

    private void CreateAlertDialog() {
        builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.do_logout);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LogOut();
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
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
        back    = findViewById(R.id.back);
        share     = findViewById(R.id.share);

        albumgeded.setOnClickListener(this);
        albumaty.setOnClickListener(this);
        back.setOnClickListener(this);
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

                Intent i = new Intent(HomeActivity.this,OfferAlbum.class);
                startActivity(i);

                break;
            case R.id.albumaty :

                Intent i2 = new Intent(HomeActivity.this, Albumaty.class);
                startActivity(i2);
                break;

            case R.id.back:
                finish();
                break;
            case R.id.share:





                        String text ="استوديو ليلاكي";
                        String link ="https://play.google.com/store/apps/details?id=com.Alatheer.Projects.laylaky";

                        Intent intent=new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_TEXT,text+"\n"+link);
                        intent.setType("text/plain");
                        startActivity(intent);


                break;
        }
    }

    private void LogOut() {
        preferences.clear();
        Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
