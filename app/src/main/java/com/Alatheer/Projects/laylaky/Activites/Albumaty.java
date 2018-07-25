package com.Alatheer.Projects.laylaky.Activites;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.Alatheer.Projects.laylaky.Adapter.AdapterAlbumaty;
import com.Alatheer.Projects.laylaky.ApiServices.Api;
import com.Alatheer.Projects.laylaky.ApiServices.Services;
import com.Alatheer.Projects.laylaky.Models.OfferModel;
import com.Alatheer.Projects.laylaky.Models.OfferModel;
import com.Alatheer.Projects.laylaky.Models.UserModel;
import com.Alatheer.Projects.laylaky.R;
import com.Alatheer.Projects.laylaky.SingleTone.Users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Albumaty extends AppCompatActivity implements Users.onCompleteListener {

    RecyclerView recyclerView;
    AdapterAlbumaty AdapterAlbumaty;
    OfferModel OfferModel;
    List<OfferModel> OfferModelList;
    private final int IMG_REQ=200;
    List<Uri> uriList;
    Users users;
    UserModel userModel;
    private ProgressBar progBar;
    private LinearLayout container;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_album);

        progBar = findViewById(R.id.progBar);
        progBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this,R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        recyclerView=findViewById(R.id.recycalbum);
        container = findViewById(R.id.container);

        users = Users.getInstance();
        users.getData(this);

        uriList = new ArrayList<>();
        OfferModelList=new ArrayList<>();


        recyclerView.setLayoutManager(new GridLayoutManager(Albumaty.this,2));
        recyclerView.setHasFixedSize(true);

        AdapterAlbumaty = new AdapterAlbumaty(OfferModelList,Albumaty.this);
        recyclerView.setAdapter(AdapterAlbumaty);
        myoffer();
    }

    private void myoffer(){

        Services services= Api.getClient().create(Services.class);
        Call<List<OfferModel>> call=services.MyOffer(userModel.getUser_id());
        call.enqueue(new Callback<List<OfferModel>>() {
            @Override
            public void onResponse(Call<List<OfferModel>> call, Response<List<OfferModel>> response) {

                if (response.isSuccessful())
                {
                    if (response.body().size()==0)
                    {
                        container.setVisibility(View.VISIBLE);
                        progBar.setVisibility(View.GONE);
                        OfferModelList.addAll(response.body());
                        AdapterAlbumaty.notifyDataSetChanged();

                    }else if (response.body().size()>0)
                    {
                        container.setVisibility(View.GONE);

                        progBar.setVisibility(View.GONE);
                        OfferModelList.addAll(response.body());
                        AdapterAlbumaty.notifyDataSetChanged();

                    }

                }

            }

            @Override
            public void onFailure(Call<List<OfferModel>> call, Throwable t) {
                Log.e("Error",t.getMessage());
                Toast.makeText(Albumaty.this, R.string.something, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public  void pos(int pos){


        OfferModel OfferModel = OfferModelList.get(pos);
        Intent i = new Intent(Albumaty.this, DetailsMyOffer.class);


        i.putExtra("title",OfferModel.getTitle());
        i.putExtra("detail",OfferModel.getDetails());
        i.putExtra("price",OfferModel.getPrice());
        i.putExtra("img", OfferModel.getImg());
        i.putExtra("id_offer", OfferModel.getOffer_id());
        i.putExtra("id_album", OfferModel.getAlbum_id());
        i.putExtra("album_size",OfferModel.getSize_offer());
        startActivity(i);


    }


    @Override
    public void OnDataSuccess(UserModel userModel) {
        this.userModel=userModel;

    }
}
