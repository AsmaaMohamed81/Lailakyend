package com.Alatheer.Projects.lailaky.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.Alatheer.Projects.lailaky.R;
import com.squareup.picasso.Picasso;

public class DetailsMyOffer extends AppCompatActivity {
    TextView title, desc, price;
    ImageView img;
    Button gallary;
    String titlee, desce, pricee, imgg, idoffer;
     String id_album;
     int album_size;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_my_offer);

        initView();

        getDataFromIntent();

        title.setText(titlee);
        desc.setText(desce);
        price.setText(pricee+" "+getString(R.string.sar));

        Picasso.with(this).load(imgg).into(img);

    }

    private void initView() {

        title = findViewById(R.id.detail_title);
        desc = findViewById(R.id.detail_desc);
        price = findViewById(R.id.detail_pric);
        gallary = findViewById(R.id.gallary);

        img = findViewById(R.id.detail_img);
        gallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailsMyOffer.this,DetailsAlbumaty.class);
                i.putExtra("id_album",id_album);
                i.putExtra("album_size",album_size);
                startActivity(i);



            }
        });



    }

    private void getDataFromIntent() {
        Intent u = getIntent();
        if (u != null) {
            titlee = u.getStringExtra("title");
            desce = u.getStringExtra("detail");
            pricee = u.getStringExtra("price");

            imgg = u.getStringExtra("img");
            idoffer = u.getStringExtra("id_offer");
            id_album=u.getStringExtra("id_album");
            album_size = Integer.parseInt(u.getStringExtra("album_size"));
        }
    }
}
