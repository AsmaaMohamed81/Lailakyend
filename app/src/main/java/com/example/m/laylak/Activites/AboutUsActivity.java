package com.example.m.laylak.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.m.laylak.Adapter.AboutAdapter;
import com.example.m.laylak.ApiServices.Api;
import com.example.m.laylak.ApiServices.Services;
import com.example.m.laylak.Models.AboutUsModel;
import com.example.m.laylak.R;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutUsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AboutAdapter aboutAdapter;
    List<AboutUsModel> modelaboutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        initView();
        GetDataFromServer();
    }

    private void GetDataFromServer() {

        Services api = Api.getClient().create(Services.class);
        Call<List<AboutUsModel>> call =api.GetAboutUs();
        call.enqueue(new Callback<List<AboutUsModel>>() {
            @Override
            public void onResponse(Call<List<AboutUsModel>> call, Response<List<AboutUsModel>> response) {

                modelaboutList.clear();
                modelaboutList.addAll( response.body());
                aboutAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<AboutUsModel>> call, Throwable t) {

            }
        });


    }

    private void initView() {
        recyclerView=findViewById(R.id.about_rec);
        modelaboutList=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(AboutUsActivity.this));
        recyclerView.setHasFixedSize(true);
        aboutAdapter = new AboutAdapter(modelaboutList,AboutUsActivity.this);
        recyclerView.setAdapter(aboutAdapter);

    }
}
