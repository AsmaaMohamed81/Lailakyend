package com.Alatheer.Projects.lailaky.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.Alatheer.Projects.lailaky.Adapter.Adapter_Paper;
import com.Alatheer.Projects.lailaky.ApiServices.Api;
import com.Alatheer.Projects.lailaky.ApiServices.Services;
import com.Alatheer.Projects.lailaky.Models.PaperModel;
import com.Alatheer.Projects.lailaky.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TypeePaperActivity extends AppCompatActivity {
    private RecyclerView paperRecyclerView;

    private List<PaperModel> mPaperModelList;

    private Adapter_Paper recyclerViewAdapter;
    private Button choose;


    private String user_id = "", id_offer = "",paper_id="";
    private String album_size = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typee_paper);
        paperRecyclerView = (RecyclerView) findViewById(R.id.paper_recyc);
        choose=findViewById(R.id.choose);



        mPaperModelList = new ArrayList<>();

        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        paperRecyclerView.setLayoutManager(recyclerLayoutManager);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(paperRecyclerView.getContext(),
                        recyclerLayoutManager.getOrientation());
        paperRecyclerView.addItemDecoration(dividerItemDecoration);


        recyclerViewAdapter = new
                Adapter_Paper(mPaperModelList, this);
        paperRecyclerView.setAdapter(recyclerViewAdapter);

        getDataFromIntent();


        Log.e("Frames paper", paper_id + "");
        recyclerViewAdapter.notifyDataSetChanged();






        Retrofit retrofit = Api.getClient();
        Services services = retrofit.create(Services.class);
        Call<List<PaperModel>> call = services.getTypePaper();
        call.enqueue(new Callback<List<PaperModel>>() {
            @Override
            public void onResponse(Call<List<PaperModel>> call, Response<List<PaperModel>> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(TypeePaperActivity.this, "yasss", Toast.LENGTH_SHORT).show();

                    if (response.body().size() > 0) {

                        mPaperModelList.addAll(response.body());
                        recyclerViewAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PaperModel>> call, Throwable t) {
                Toast.makeText(TypeePaperActivity.this, "nooo" + t, Toast.LENGTH_SHORT).show();

            }
        });

    }


//    private List<PaperModel> getBrands(){
//        List<PaperModel> modelList = new ArrayList<PaperModel>();
//        modelList.add(new PaperModel("1g", R.drawable.a17,"1"));
//        modelList.add(new PaperModel("2g", R.drawable.a17,"2"));
//        modelList.add(new PaperModel("3g", R.drawable.a17,"3"));
//        modelList.add(new PaperModel("4g", R.drawable.a17,"4"));
//        modelList.add(new PaperModel("5g", R.drawable.a17,"5"));
//        modelList.add(new PaperModel("6g", R.drawable.a17,"6"));
//        modelList.add(new PaperModel("7g", R.drawable.a17,"7"));
//        modelList.add(new PaperModel("8g", R.drawable.a17,"8"));
//        modelList.add(new PaperModel("9g", R.drawable.a17,"9"));
//        modelList.add(new PaperModel("10g", R.drawable.a17,"10"));
//        modelList.add(new PaperModel("11g", R.drawable.a17,"11"));
//
//        return modelList;
//    }


    public  void pos(int pos) {

        PaperModel paperModel= mPaperModelList.get(pos);
        paper_id= paperModel.getpaper_id();

        Intent intent = new Intent(TypeePaperActivity.this,FramesActivity.class);

        //intent.putExtra("data",encoded);
        intent.putExtra("album_size",album_size);
        intent.putExtra("id_offer",id_offer);
        intent.putExtra("user_id",user_id);
        intent.putExtra("paper_id",paper_id);

        startActivity(intent);
        finish();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            album_size = intent.getStringExtra("album_size");
            user_id = intent.getStringExtra("user_id");
            id_offer = intent.getStringExtra("id_offer");

            Log.e("Frames albumsize", album_size + "");
            Log.e("Frames id", user_id + "");
            Log.e("Frames offer", id_offer + "");



        }
    }
}