package com.Alatheer.Projects.lailaky.Activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.Alatheer.Projects.lailaky.Adapter.CustomGalleryAdapter;
import com.Alatheer.Projects.lailaky.Adapter.GalleryAdapter;
import com.Alatheer.Projects.lailaky.ApiServices.Api;
import com.Alatheer.Projects.lailaky.ApiServices.Services;
import com.Alatheer.Projects.lailaky.ApiServices.Tags;
import com.Alatheer.Projects.lailaky.Models.GalleryImagesModel;
import com.Alatheer.Projects.lailaky.Models.ImgModel;
import com.Alatheer.Projects.lailaky.R;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsAlbumaty extends AppCompatActivity {
    private DiscreteScrollView recView;
    private RecyclerView.Adapter adapter;
    private ProgressBar bar;
    private Toolbar toolBar;
    private int  album_size;
    //public boolean isContextMode=false;
    Gallery simpleGallery;
    CustomGalleryAdapter customGalleryAdapter;
    ImageView selectedImageView;
    private String album_id;
     List<ImgModel> uriList1,uriList2;
     ImgModel imgModel;
     TextView counter;
     private int count=0;
     private List<ImgModel> selectedImagesList;
     private final int IMG_REQ=1230;
     private Bitmap bitmap;
     private List<String> encodedImageList;
     private List<Bitmap> bitmaps;
     private ProgressDialog dialog,dialog2;
     private List<GalleryImagesModel> galleryImagesModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_albumaty);
        getDataFromIntent();

        initView();

        recView.setItemTransformer(new ScaleTransformer.Builder()
                .setMaxScale(1.05f)
                .setMinScale(0.8f)
                .setPivotX(Pivot.X.CENTER) // CENTER is a default one
                .setPivotY(Pivot.Y.BOTTOM) // CENTER is a default one
                .build());

       recView.addOnItemChangedListener(new DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>() {
           @Override
           public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
               int page=adapterPosition+1;
               counter.setText("<"+page+"/"+uriList1.size()+">");
           }
       });


    }



    private void initView() {
        selectedImagesList = new ArrayList<>();
        uriList1=new ArrayList<>();
        uriList2=new ArrayList<>();
        galleryImagesModelList = new ArrayList<>();
        encodedImageList = new ArrayList<>();

        bar = findViewById(R.id.progBar);
        bar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this,R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        recView = findViewById(R.id.recView);
        adapter = new GalleryAdapter(galleryImagesModelList,this);
        recView.setAdapter(adapter);
        counter = findViewById(R.id.counter);
        toolBar = findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getData();

    }

    private void CreateProgress()
    {
        ProgressBar bar = new ProgressBar(this);
        Drawable drawable = bar.getIndeterminateDrawable().mutate();
        drawable.setColorFilter(ContextCompat.getColor(this,R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.upload_img));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setIndeterminateDrawable(drawable);

    }
    private void CreatedeleteProgress()
    {
        ProgressBar bar = new ProgressBar(this);
        Drawable drawable = bar.getIndeterminateDrawable().mutate();
        drawable.setColorFilter(ContextCompat.getColor(this,R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        dialog2 = new ProgressDialog(this);
        dialog2.setMessage(getString(R.string.delete_img));
        dialog2.setCancelable(true);
        dialog2.setCanceledOnTouchOutside(false);
        dialog2.setIndeterminateDrawable(drawable);

    }
    private void getData() {

        Services services = Api.getClient().create(Services.class);
        Call<List<ImgModel>> call = services.GallaryMyOffer(album_id);
        call.enqueue(new Callback<List<ImgModel>>() {
            @Override
            public void onResponse(Call<List<ImgModel>> call, Response<List<ImgModel>> response) {

                if (response.isSuccessful())
                {
                    bar.setVisibility(View.GONE);

                    uriList1.clear();

                    uriList1.addAll(response.body());

                    UpdateAdapterUI(uriList1);

                    /* adapter.notifyDataSetChanged();
                    bar.setVisibility(View.GONE);*/
                }

            }

            @Override
            public void onFailure(Call<List<ImgModel>> call, Throwable t) {

                Log.e("Error",t.getMessage());
                Toast.makeText(DetailsAlbumaty.this, "Something went haywire", Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void UpdateAdapterUI(List<ImgModel> uriList1) {
        for (int i=0;i<uriList1.size();i+=2)
        {
            GalleryImagesModel galleryImagesModel = new GalleryImagesModel();
            galleryImagesModel.setFirst_image_id(uriList1.get(i).getImage_id());
            galleryImagesModel.setFirst_image_name(uriList1.get(i).getImage());

            if ((i+1)<uriList1.size())
            {
                galleryImagesModel.setSecond_image_id(uriList1.get(i+1).getImage_id());
                galleryImagesModel.setSecond_image_name(uriList1.get(i+1).getImage());

            }else
                {
                    galleryImagesModel.setSecond_image_id("");
                    galleryImagesModel.setSecond_image_name("");
                }

                galleryImagesModelList.add(galleryImagesModel);
        }

        adapter.notifyDataSetChanged();


    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {

            if (intent.hasExtra("id_album"))
            {
                album_id = intent.getStringExtra("id_album");
                album_size = intent.getIntExtra("album_size",0);
            }
        }
    }



}
