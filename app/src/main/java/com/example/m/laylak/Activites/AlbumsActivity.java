package com.example.m.laylak.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.m.laylak.R;

public class AlbumsActivity extends AppCompatActivity {
ImageButton albumgeded,albumaty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        albumgeded=findViewById(R.id.albumgeded);
        albumaty=findViewById(R.id.albumaty);



        albumgeded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(AlbumsActivity.this,OfferAlbum.class);
                startActivity(i);
                
            }
        });

        albumaty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(AlbumsActivity.this, Albumaty.class);
                startActivity(i);

            }
        });
    }


}
