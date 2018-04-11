package com.Alatheer.Projects.laylaky.Activites;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.Alatheer.Projects.laylaky.Adapter.CustomGalleryAdapter;
import com.Alatheer.Projects.laylaky.Adapter.GalleryAdapter;
import com.Alatheer.Projects.laylaky.ApiServices.Api;
import com.Alatheer.Projects.laylaky.ApiServices.Services;
import com.Alatheer.Projects.laylaky.Models.ImgModel;
import com.Alatheer.Projects.laylaky.Models.OfferModel;
import com.Alatheer.Projects.laylaky.R;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.Alatheer.Projects.laylaky.ApiServices.Tags.ImgPath;

public class DetailsAlbumaty extends AppCompatActivity {
    private RecyclerViewPager recView;
    private RecyclerViewPager.LayoutManager manager;
    private RecyclerViewPager.Adapter adapter;
    private ProgressBar bar;
    Gallery simpleGallery;
    int fi=0;
    CustomGalleryAdapter customGalleryAdapter;
    ImageView selectedImageView;
    // array of images

    List<ImgModel> uriList;
     String idoffer;
     ImgModel imgModel;
     TextView counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_albumaty);

        initView();
        getDataFromIntent();
        recView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


            }

            @Override
            public void onScrolled(final RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int currPos = recView.getCurrentPosition()+1;
                        int total = recView.getAdapter().getItemCount();

                        counter.setText("<"+currPos+"/"+total+">");
                        Log.e("curr",recView.getCurrentPosition()+"");

                    }
                },100);
            }
        });

        /*final SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recView);
        recView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int targetSnapPosition = snapHelper.findTargetSnapPosition(recyclerView.getLayoutManager(), dx, dx);
                Log.e("posssssss",targetSnapPosition+"");
            }
        });
*/
       /* simpleGallery =  findViewById(R.id.simpleGallery); // get the reference of Gallery
        selectedImageView =  findViewById(R.id.selectedImageView); // get the reference of ImageView
*/
        //getDataFromIntent();
//


//        Picasso.with(DetailsAlbumaty.this).load(ImgPath+imgModel.getImage()).into(selectedImageView);

      /*  customGalleryAdapter = new CustomGalleryAdapter(this, uriList); // initialize the adapter
        simpleGallery.setAdapter(customGalleryAdapter);
*/



        /*simpleGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set the selected image in the ImageView
                Toast.makeText(DetailsAlbumaty.this, position+"", Toast.LENGTH_SHORT).show();
                imgModel=  uriList.get(position);
                Picasso.with(DetailsAlbumaty.this).load(ImgPath+imgModel.getImage()).into(selectedImageView);
            }
        });
*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }

    private void initView() {
        uriList=new ArrayList<>();
        bar = findViewById(R.id.progBar);
        bar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this,R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        recView = findViewById(R.id.recView);
        manager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recView.setLayoutManager(manager);
        adapter = new GalleryAdapter(uriList,this);
        recView.setAdapter(adapter);

        counter = findViewById(R.id.counter);
    }

    private void getData() {

        Services services = Api.getClient().create(Services.class);
        Call<List<ImgModel>> call = services.GallaryMyOffer(idoffer);
        call.enqueue(new Callback<List<ImgModel>>() {
            @Override
            public void onResponse(Call<List<ImgModel>> call, Response<List<ImgModel>> response) {

                if (response.isSuccessful())
                {
                    uriList.clear();
                    uriList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                    bar.setVisibility(View.GONE);
                }

/*

              //  Toast.makeText(DetailsAlbumaty.this, "success", Toast.LENGTH_SHORT).show();
                uriList.clear();
                uriList.addAll(response.body());
//                Toast.makeText(DetailsAlbumaty.this, ""+response.body().get(0).getImage(), Toast.LENGTH_SHORT).show();
               // Toast.makeText(DetailsAlbumaty.this, ""+uriList.get(1), Toast.LENGTH_SHORT).show();
                customGalleryAdapter.notifyDataSetChanged();
                Picasso.with(DetailsAlbumaty.this).load(ImgPath+response.body().get(0).getImage()).into(selectedImageView);


*/


            }

            @Override
            public void onFailure(Call<List<ImgModel>> call, Throwable t) {
                Log.e("Error",t.getMessage());
                Toast.makeText(DetailsAlbumaty.this, "Something went haywire", Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {

            if (intent.hasExtra("id_album"))
            {
                idoffer = intent.getStringExtra("id_album");

            }
        }
    }




}
