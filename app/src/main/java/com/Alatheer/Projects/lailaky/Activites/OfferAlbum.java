package com.Alatheer.Projects.lailaky.Activites;

import android.content.Context;
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

import com.Alatheer.Projects.lailaky.Adapter.AdapterAlbums;
import com.Alatheer.Projects.lailaky.ApiServices.Api;
import com.Alatheer.Projects.lailaky.ApiServices.Services;
import com.Alatheer.Projects.lailaky.Models.OfferModel;
import com.Alatheer.Projects.lailaky.R;
import com.Alatheer.Projects.lailaky.SingleTone.FinalAlbumImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class OfferAlbum extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterAlbums adapterAlbums;
    private OfferModel OfferModel;
    private List<OfferModel> OfferModelList;
    private String user_type;
    private LinearLayout container;
    private ProgressBar progBar;

    private FinalAlbumImage instance;



    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);

        super.attachBaseContext(LanguageHelper.onAttach(newBase, Paper.book().read("language", Locale.getDefault().getLanguage())));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_album);
        instance = FinalAlbumImage.getInstance();
        getDataFromIntent();
        progBar = findViewById(R.id.progBar);
        progBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this,R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        container = findViewById(R.id.container);

        recyclerView=findViewById(R.id.recycalbum);

        OfferModelList=new ArrayList<>();



        recyclerView.setLayoutManager(new GridLayoutManager(OfferAlbum.this,2));
        recyclerView.setHasFixedSize(true);

      adapterAlbums = new AdapterAlbums(OfferModelList,OfferAlbum.this);
        recyclerView.setAdapter(adapterAlbums);

        Retrofit retrofit=Api.getClient();
        Services service= retrofit.create(Services.class);
        Call<List<OfferModel>> call=service.GetOffers();

        call.enqueue(new Callback<List<com.Alatheer.Projects.lailaky.Models.OfferModel>>() {
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

        instance.ClearList();
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
