package com.example.m.laylak.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.m.laylak.Adapter.AdapterAlbums;
import com.example.m.laylak.ApiServices.Api;
import com.example.m.laylak.ApiServices.Services;
import com.example.m.laylak.Models.OfferModel;
import com.example.m.laylak.R;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class OfferAlbum extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterAlbums adapterAlbums;
    OfferModel OfferModel;
    List<OfferModel> OfferModelList;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_album);
        
        
        recyclerView=findViewById(R.id.recycalbum);

        OfferModelList=new ArrayList<>();



        recyclerView.setLayoutManager(new GridLayoutManager(OfferAlbum.this,3));
        recyclerView.setHasFixedSize(true);

      adapterAlbums = new AdapterAlbums(OfferModelList,OfferAlbum.this);
        recyclerView.setAdapter(adapterAlbums);

        Retrofit retrofit=Api.getClient();
        Services service= retrofit.create(Services.class);
        Call<List<OfferModel>> call=service.GetOffers();

        call.enqueue(new Callback<List<com.example.m.laylak.Models.OfferModel>>() {
            @Override
            public void onResponse(Call<List<OfferModel>> call, Response<List<OfferModel>> response) {

                OfferModelList.addAll(response.body());
                adapterAlbums.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<OfferModel>> call, Throwable t) {

            }
        });




    }

    public  void pos(int pos){


        OfferModel OfferModel = OfferModelList.get(pos);
        Intent i = new Intent(OfferAlbum.this, DetailOffer.class);


        i.putExtra("title",OfferModel.getTitle());
        i.putExtra("detail",OfferModel.getDetails());
        i.putExtra("price",OfferModel.getPrice());
        i.putExtra("img", OfferModel.getImg());


        startActivity(i);


    }


}
