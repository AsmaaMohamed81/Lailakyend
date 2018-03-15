package com.example.m.laylak.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.m.laylak.R;

public class LoginActivity extends AppCompatActivity {
    Button login;

  TextView regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=findViewById(R.id.log);
        regist=findViewById(R.id.regist);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,AlbumsActivity.class);
                startActivity(intent);
            }
        });

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,signup.class);
                startActivity(intent);
            }
        });
    }
}
