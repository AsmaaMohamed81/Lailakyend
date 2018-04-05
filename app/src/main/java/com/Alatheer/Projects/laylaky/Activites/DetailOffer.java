package com.Alatheer.Projects.laylaky.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.Alatheer.Projects.laylaky.Models.UserModel;
import com.Alatheer.Projects.laylaky.R;
import com.Alatheer.Projects.laylaky.SingleTone.Users;
import com.squareup.picasso.Picasso;

public class DetailOffer extends AppCompatActivity implements Users.onCompleteListener{
TextView title,desc,price;
ImageView img;
Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_offer);

        users = Users.getInstance();
        users.getData(this);
        title=findViewById(R.id.detail_title);
        desc=findViewById(R.id.detail_desc);
        price=findViewById(R.id.detail_pric);

        img=findViewById(R.id.detail_img);

        Intent u = getIntent();
       String titlee = u.getStringExtra("title");
        String desce = u.getStringExtra("detail");
        String pricee = u.getStringExtra("price");

        String imgg =u.getStringExtra("img");

        title.setText(titlee);
        desc.setText(desce);
        price.setText(pricee);

        Picasso.with(this).load(imgg).into(img);

    }

    @Override
    public void OnDataSuccess(UserModel userModel) {
        Log.e("id",userModel.getUser_id());
    }
}
