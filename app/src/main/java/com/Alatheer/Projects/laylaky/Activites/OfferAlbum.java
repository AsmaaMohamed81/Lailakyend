package com.Alatheer.Projects.laylaky.Activites;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.Alatheer.Projects.laylaky.Adapter.AdapterAlbums;
import com.Alatheer.Projects.laylaky.ApiServices.Api;
import com.Alatheer.Projects.laylaky.ApiServices.Services;
import com.Alatheer.Projects.laylaky.Models.OfferModel;
import com.Alatheer.Projects.laylaky.R;

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
    private String user_type;
    private LinearLayout container;
    private ProgressBar progBar;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_album);
        getDataFromIntent();
        progBar = findViewById(R.id.progBar);
        progBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this,R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        container = findViewById(R.id.container);

        recyclerView=findViewById(R.id.recycalbum);

        OfferModelList=new ArrayList<>();



        recyclerView.setLayoutManager(new GridLayoutManager(OfferAlbum.this,3));
        recyclerView.setHasFixedSize(true);

      adapterAlbums = new AdapterAlbums(OfferModelList,OfferAlbum.this);
        recyclerView.setAdapter(adapterAlbums);

        Retrofit retrofit=Api.getClient();
        Services service= retrofit.create(Services.class);
        Call<List<OfferModel>> call=service.GetOffers();

        call.enqueue(new Callback<List<com.Alatheer.Projects.laylaky.Models.OfferModel>>() {
            @Override
            public void onResponse(Call<List<OfferModel>> call, Response<List<OfferModel>> response) {

                if (response.isSuccessful())
                {
                    if (response.body().size()>0)
                    {
                        progBar.setVisibility(View.GONE);
                        container.setVisibility(View.GONE);
                        OfferModelList.addAll(response.body());
                        adapterAlbums.notifyDataSetChanged();
                    }else
                        {
                            progBar.setVisibility(View.GONE);
                            container.setVisibility(View.VISIBLE);
                        }
                }


            }

            @Override
            public void onFailure(Call<List<OfferModel>> call, Throwable t) {
               // progBar.setVisibility(View.GONE);
                Log.e("Error",t.getMessage());
                Toast.makeText(OfferAlbum.this, getString(R.string.something), Toast.LENGTH_SHORT).show();
            }
        });




    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("user_type"))
            {
                user_type = intent.getStringExtra("user_type");
            }
    }
    }

    public  void pos(int pos){


        OfferModel OfferModel = OfferModelList.get(pos);
        Intent i = new Intent(OfferAlbum.this, DetailOffer.class);


        i.putExtra("title",OfferModel.getTitle());
        i.putExtra("detail",OfferModel.getDetails());
        i.putExtra("price",OfferModel.getPrice());
        i.putExtra("img", OfferModel.getImg());
        i.putExtra("id_offer", OfferModel.getOffer_id());
        i.putExtra("size_offer",OfferModel.getSize_offer());
        i.putExtra("user_type",user_type);

        startActivity(i);


    }


}
