package com.example.m.laylak.Activites;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.m.laylak.Adapter.AdapterAlbumaty;
import com.example.m.laylak.Models.Modelalbums;
import com.example.m.laylak.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Albumaty extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterAlbumaty AdapterAlbumaty;
    Modelalbums modelalbums;
    List<Modelalbums> modelalbumsList;
    private final int IMG_REQ=200;
    List<Uri> uriList;

    int[] img={R.drawable.hhh,R.drawable.hhh,R.drawable.hhh,R.drawable.hhh};
    String[] title={"البوم اطفالي","البوم خطوبتي","ااسم الالبوم","البوم فرحي"};
    String [] desc ={"asadjksjdksjdskjdsklds","asjahdsjkhdkshkdskdh","asadjksjdksjdskjdsklds","asjahdsjkhdkshkdskdh","asadjksjdksjdskjdsklds","asjahdsjkhdkshkdskdh"};

    String[] pric = {"200","400","200","400","200","400"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_album);
        recyclerView=findViewById(R.id.recycalbum);
        uriList = new ArrayList<>();
        modelalbumsList=new ArrayList<>();

        for (int i=0;i<title.length;i++) {

            modelalbums=new Modelalbums(title[i],img[i]);
            modelalbumsList.add(modelalbums);
        }

        recyclerView.setLayoutManager(new GridLayoutManager(Albumaty.this,2));
        recyclerView.setHasFixedSize(true);

        AdapterAlbumaty = new AdapterAlbumaty(modelalbumsList,Albumaty.this);
        recyclerView.setAdapter(AdapterAlbumaty);
    }
    public void setPos(int pos)
    {
        //Modelalbums modelalbums = modelalbumsList.get(pos);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        startActivityForResult(intent,IMG_REQ);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==IMG_REQ && resultCode == RESULT_OK && data!=null)
        {
            ClipData clipData = data.getClipData();
         //   Log.e("eddddd",""+clipData.getItemCount()+"   "+clipData.getItemAt(0));
            for (int index =0;index<clipData.getItemCount();index++)
            {
                ClipData.Item item = clipData.getItemAt(index);
                Uri uri = item.getUri();
                uriList.add(uri);
            }

            Intent intent = new Intent(Albumaty.this,DetailsAlbumaty.class);

            intent.putExtra("details", (Serializable) uriList);

            startActivity(intent);

            Toast.makeText(this, ""+uriList.get(0), Toast.LENGTH_SHORT).show();
        }
    }
}
