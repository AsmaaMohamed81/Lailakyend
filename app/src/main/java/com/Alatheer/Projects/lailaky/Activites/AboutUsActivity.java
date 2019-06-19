package com.Alatheer.Projects.lailaky.Activites;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.Alatheer.Projects.lailaky.Adapter.AboutAdapter;
import com.Alatheer.Projects.lailaky.ApiServices.Api;
import com.Alatheer.Projects.lailaky.ApiServices.Services;
import com.Alatheer.Projects.lailaky.Models.AboutUsModel;
import com.Alatheer.Projects.lailaky.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutUsActivity extends AppCompatActivity {

    //AIzaSyDIW3RKUNVKCEWQxIt77BD0MP_RGwbII9Y

    RecyclerView recyclerView;
    AboutAdapter aboutAdapter;
    List<AboutUsModel> modelaboutList;
    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(LanguageHelper.onAttach(newBase, Paper.book().read("language", Locale.getDefault().getLanguage())));
    }


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
        aboutAdapter = new AboutAdapter(modelaboutList,AboutUsActivity.this,recyclerView);
        recyclerView.setAdapter(aboutAdapter);

    }
}
