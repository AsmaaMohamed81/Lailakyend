package com.Alatheer.Projects.lailaky.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.Alatheer.Projects.lailaky.R;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
Button offer,about,contact;
String user_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();
        getDataFromIntent();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent!=null)
        {
            if (intent.hasExtra("user_type"))
            {
                user_type = intent.getStringExtra("user_type");
            }
        }
    }

    private void initView() {
        offer=findViewById(R.id.offers);
        about=findViewById(R.id.about_us);
        contact=findViewById(R.id.contact);


        offer.setOnClickListener(this);
        about.setOnClickListener(this);
        contact.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.offers:

                    Intent i = new Intent(UserActivity.this,OfferAlbum.class);
                    i.putExtra("user_type",user_type);
                    startActivity(i);

                    break;
                case R.id.about_us :

                    Intent i2 = new Intent(UserActivity.this, AboutUsActivity.class);
                    startActivity(i2);
                    break;
                case R.id.contact :

                    Intent i3 = new Intent(UserActivity.this, ContactActivity.class);
                    startActivity(i3);
                    break;



        }
    }
}
