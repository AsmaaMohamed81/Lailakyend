package com.Alatheer.Projects.lailaky.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.Alatheer.Projects.lailaky.Adapter.Adapter_Paper;
import com.Alatheer.Projects.lailaky.Models.PaperModel;
import com.Alatheer.Projects.lailaky.R;

import java.util.ArrayList;
import java.util.List;

public class TypeePaperActivity extends AppCompatActivity {
    private RecyclerView paperRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typee_paper);
        paperRecyclerView = (RecyclerView) findViewById(R.id.paper_recyc);

        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        paperRecyclerView.setLayoutManager(recyclerLayoutManager);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(paperRecyclerView.getContext(),
                        recyclerLayoutManager.getOrientation());
        paperRecyclerView.addItemDecoration(dividerItemDecoration);


        Adapter_Paper recyclerViewAdapter = new
                Adapter_Paper(getBrands(),this);
        paperRecyclerView.setAdapter(recyclerViewAdapter);
    }

    private List<PaperModel> getBrands(){
        List<PaperModel> modelList = new ArrayList<PaperModel>();
        modelList.add(new PaperModel("1g", R.drawable.a17,"1"));
        modelList.add(new PaperModel("2g", R.drawable.a17,"2"));
        modelList.add(new PaperModel("3g", R.drawable.a17,"3"));
        modelList.add(new PaperModel("4g", R.drawable.a17,"4"));
        modelList.add(new PaperModel("5g", R.drawable.a17,"5"));
        modelList.add(new PaperModel("6g", R.drawable.a17,"6"));
        modelList.add(new PaperModel("7g", R.drawable.a17,"7"));
        modelList.add(new PaperModel("8g", R.drawable.a17,"8"));
        modelList.add(new PaperModel("9g", R.drawable.a17,"9"));
        modelList.add(new PaperModel("10g", R.drawable.a17,"10"));
        modelList.add(new PaperModel("11g", R.drawable.a17,"11"));

        return modelList;
    }}
