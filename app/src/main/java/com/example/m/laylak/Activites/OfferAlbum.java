package com.example.m.laylak.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.m.laylak.Adapter.AdapterAlbums;
import com.example.m.laylak.Models.Modelalbums;
import com.example.m.laylak.R;

import java.util.ArrayList;
import java.util.List;

public class OfferAlbum extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterAlbums adapterAlbums;
    Modelalbums modelalbums;
    List<Modelalbums> modelalbumsList;

    int[] img={R.drawable.album1,R.drawable.album2,R.drawable.album3,R.drawable.album1,R.drawable.album2,R.drawable.album3};
    String[] title={"الالبوم الاول","الالبوم الثاني","الالبوم الاول","الالبوم الثاني","الالبوم الثالث","الالبوم الثالث"};
    String [] desc ={"asadjksjdksjdskjdsklds","asjahdsjkhdkshkdskdh","asadjksjdksjdskjdsklds","asjahdsjkhdkshkdskdh","asadjksjdksjdskjdsklds","asjahdsjkhdkshkdskdh"};

    String[] pric = {"200","400","200","400","200","400"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_album);
        recyclerView=findViewById(R.id.recycalbum);

        modelalbumsList=new ArrayList<>();

        for (int i=0;i<title.length;i++) {

            modelalbums=new Modelalbums(title[i],img[i],desc[i],pric[i]);
            modelalbumsList.add(modelalbums);
        }

        recyclerView.setLayoutManager(new GridLayoutManager(OfferAlbum.this,3));
        recyclerView.setHasFixedSize(true);

        adapterAlbums = new AdapterAlbums(modelalbumsList,OfferAlbum.this);
        recyclerView.setAdapter(adapterAlbums);
    }

    public  void pos(int pos){


        Modelalbums modelalbums = modelalbumsList.get(pos);
        Intent i = new Intent(OfferAlbum.this, DetailOffer.class);


        i.putExtra("title",modelalbums.getTitle());
        i.putExtra("detail",modelalbums.getDesc());
        i.putExtra("price",modelalbums.getPrice());
        i.putExtra("img",modelalbums.getImg());


        startActivity(i);


    }
}
