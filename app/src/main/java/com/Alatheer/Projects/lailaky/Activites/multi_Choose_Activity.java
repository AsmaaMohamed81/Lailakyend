package com.Alatheer.Projects.lailaky.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.Alatheer.Projects.lailaky.Adapter.Adapter_Paper;
import com.Alatheer.Projects.lailaky.Models.PaperModel;
import com.Alatheer.Projects.lailaky.R;

import java.util.List;

public class multi_Choose_Activity extends AppCompatActivity {

    private Button cover,frames,twopages;

    private RecyclerView paperRecyclerView;

    private List<PaperModel> mPaperModelList;

    private Adapter_Paper recyclerViewAdapter;
    private Button choose;


    private String user_id = "", id_offer = "",paper_id="";
    private String album_size = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi__choose_);
        getDataFromIntent();
        initview();
    }

    private void initview() {
        cover=findViewById(R.id.cover);
        frames=findViewById(R.id.frames);
        twopages=findViewById(R.id.twopages);




        cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(multi_Choose_Activity.this,makeCoverActivity.class);

                intent.putExtra("album_size",album_size);
                intent.putExtra("id_offer",id_offer);
                intent.putExtra("user_id",user_id);
                intent.putExtra("paper_id",paper_id);

                startActivity(intent);




            }
        });
        frames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(multi_Choose_Activity.this,FramesActivity.class);

                intent.putExtra("album_size",album_size);
                intent.putExtra("id_offer",id_offer);
                intent.putExtra("user_id",user_id);
                intent.putExtra("paper_id",paper_id);

                startActivity(intent);




            }
        });

        twopages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(multi_Choose_Activity.this,twopagesActivity.class);

                intent.putExtra("album_size",album_size);
                intent.putExtra("id_offer",id_offer);
                intent.putExtra("user_id",user_id);
                intent.putExtra("paper_id",paper_id);

                startActivity(intent);

            }
        });


    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            album_size = intent.getStringExtra("album_size");
            user_id = intent.getStringExtra("user_id");
            id_offer = intent.getStringExtra("id_offer");
            paper_id=intent.getStringExtra("paper_id");

            Log.e("Frames albumsize", album_size + "");
            Log.e("Frames id", user_id + "");
            Log.e("Frames offer", id_offer + "");
            Log.e("Frames paper_id", paper_id + "");




        }}
}
