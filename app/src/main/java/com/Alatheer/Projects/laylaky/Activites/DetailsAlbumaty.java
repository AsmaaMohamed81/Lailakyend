package com.Alatheer.Projects.laylaky.Activites;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.Alatheer.Projects.laylaky.Adapter.CustomGalleryAdapter;
import com.Alatheer.Projects.laylaky.ApiServices.Api;
import com.Alatheer.Projects.laylaky.ApiServices.Services;
import com.Alatheer.Projects.laylaky.Models.ImgModel;
import com.Alatheer.Projects.laylaky.Models.OfferModel;
import com.Alatheer.Projects.laylaky.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.Alatheer.Projects.laylaky.ApiServices.Tags.ImgPath;

public class DetailsAlbumaty extends AppCompatActivity {
    Gallery simpleGallery;
    CustomGalleryAdapter customGalleryAdapter;
    ImageView selectedImageView;
    // array of images

    List<ImgModel> uriList;
     String idoffer;
     ImgModel imgModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_albumaty);
        uriList=new ArrayList<>();

        simpleGallery = (Gallery) findViewById(R.id.simpleGallery); // get the reference of Gallery
        selectedImageView = (ImageView) findViewById(R.id.selectedImageView); // get the reference of ImageView

        customGalleryAdapter = new CustomGalleryAdapter(this, uriList); // initialize the adapter
        simpleGallery.setAdapter(customGalleryAdapter);

        simpleGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set the selected image in the ImageView
                imgModel=  uriList.get(position);
                Picasso.with(DetailsAlbumaty.this).load(ImgPath+imgModel.getImage()).into(selectedImageView);
            }
        });

        getntent();
        getDataFromIntent();
    }

    private void getDataFromIntent() {

        Services services = Api.getClient().create(Services.class);
        Call<List<ImgModel>> call = services.GallaryMyOffer(idoffer);
        call.enqueue(new Callback<List<ImgModel>>() {
            @Override
            public void onResponse(Call<List<ImgModel>> call, Response<List<ImgModel>> response) {


              //  Toast.makeText(DetailsAlbumaty.this, "success", Toast.LENGTH_SHORT).show();
                uriList.clear();
                uriList.addAll(response.body());
//                Toast.makeText(DetailsAlbumaty.this, ""+response.body().get(0).getImage(), Toast.LENGTH_SHORT).show();
               // Toast.makeText(DetailsAlbumaty.this, ""+uriList.get(1), Toast.LENGTH_SHORT).show();
                customGalleryAdapter.notifyDataSetChanged();



            }

            @Override
            public void onFailure(Call<List<ImgModel>> call, Throwable t) {
                Toast.makeText(DetailsAlbumaty.this, ""+t, Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void getntent() {
        Intent u = getIntent();
        if (u != null) {

            idoffer = u.getStringExtra("id_album");
            Toast.makeText(this, "fff"+idoffer, Toast.LENGTH_SHORT).show();
        }
    }

//    private void UpdateUI(List<Uri> uriList) {
//
//        customGalleryAdapter = new CustomGalleryAdapter(this, uriList); // initialize the adapter
//        simpleGallery.setAdapter(customGalleryAdapter);
//
//    }


}
