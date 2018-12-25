package com.Alatheer.Projects.lailaky.Activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.Alatheer.Projects.lailaky.Adapter.MyViewPagerDisplayAdapter;
import com.Alatheer.Projects.lailaky.ApiServices.Api;
import com.Alatheer.Projects.lailaky.ApiServices.Services;
import com.Alatheer.Projects.lailaky.Fragments.FragmentDisplayCoverImage;
import com.Alatheer.Projects.lailaky.Fragments.FragmentDisplayOneImage;
import com.Alatheer.Projects.lailaky.Fragments.FragmentDisplayTwoImage;
import com.Alatheer.Projects.lailaky.Models.GalleryImagesModel;
import com.Alatheer.Projects.lailaky.Models.ImgModel;
import com.Alatheer.Projects.lailaky.R;
import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsAlbumaty extends AppCompatActivity {
   /* private DiscreteScrollView recView;
    private RecyclerView.Adapter adapter;
    private ProgressBar bar;*/
    private Toolbar toolBar;
    private int  album_size;
    //public boolean isContextMode=false;

    private String album_id;
     List<ImgModel> uriList1,uriList2;
     TextView counter;
     private ProgressDialog dialog,dialog2;
     private List<GalleryImagesModel> galleryImagesModelList;
     private ViewPager pager;
     private MyViewPagerDisplayAdapter myViewPagerDisplayAdapter;
     private List<Fragment> fragmentList;
     private TextView tv_page1,tv_page2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_albumaty);
        getDataFromIntent();

        initView();

       /* recView.setItemTransformer(new ScaleTransformer.Builder()
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
       });*/


    }



    private void initView() {
        fragmentList = new ArrayList<>();
        uriList1=new ArrayList<>();
        uriList2=new ArrayList<>();
        galleryImagesModelList = new ArrayList<>();
        pager =findViewById(R.id.pager);
        pager.setPageTransformer(true,new CubeOutTransformer());

        tv_page1 =findViewById(R.id.tv_page1);
        tv_page2 =findViewById(R.id.tv_page2);

        counter = findViewById(R.id.counter);
        toolBar = findViewById(R.id.toolBar);

        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        CreateProgress();
        getData();

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                int page = position+1;
                int total_page = fragmentList.size();
                Log.e("page",page+"__");
                counter.setText("("+page+"/"+total_page+")");

                if (fragmentList.get(0) instanceof FragmentDisplayCoverImage)
                {
                    if (position!=0)
                    {
                        int prev_page = ((position)*2)-1;
                        int next_page = (position)*2;

                        tv_page1.setText("("+prev_page+")");
                        tv_page2.setText("("+next_page+")");

                        Log.e("prev",prev_page+"_");
                        Log.e("next",next_page+"_");

                    }else
                        {
                            tv_page1.setText("");
                            tv_page2.setText("");
                            counter.setText(getString(R.string.cov_photo));
                        }

                }else
                    {
                        int prev_page = ((position+1)*2)-1;
                        int next_page = (position+1)*2;
                        tv_page1.setText("("+prev_page+")");
                        tv_page2.setText("("+next_page+")");

                        Log.e("prev",prev_page+"_");
                        Log.e("next",next_page+"_");
                    }



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    private void CreateProgress()
    {
        ProgressBar bar = new ProgressBar(this);
        Drawable drawable = bar.getIndeterminateDrawable().mutate();
        drawable.setColorFilter(ContextCompat.getColor(this,R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.lod));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setIndeterminateDrawable(drawable);


    }

    private void getData() {

        dialog.show();
        Services services = Api.getClient().create(Services.class);
        Call<List<ImgModel>> call = services.GallaryMyOffer(album_id);
        call.enqueue(new Callback<List<ImgModel>>() {
            @Override
            public void onResponse(Call<List<ImgModel>> call, Response<List<ImgModel>> response) {

                if (response.isSuccessful())
                {
                    dialog.dismiss();
                    //bar.setVisibility(View.GONE);

                    uriList1.clear();

                    uriList1.addAll(response.body());

                    UpdateAdapterUI(uriList1);

                    /* adapter.notifyDataSetChanged();
                    bar.setVisibility(View.GONE);*/
                }

            }

            @Override
            public void onFailure(Call<List<ImgModel>> call, Throwable t) {
                dialog.dismiss();
                Log.e("Error",t.getMessage());
                Toast.makeText(DetailsAlbumaty.this, "Something went haywire", Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void UpdateAdapterUI(List<ImgModel> uriList1) {
        Log.e("sicexxxxxxxx",uriList1.size()+"___");
        for (int i=0;i<uriList1.size();i+=2)
        {

            Log.e("type",uriList1.get(i).getType());
            Log.e("image",uriList1.get(i).getImage());

            if (uriList1.get(i).getType().equals("0")||uriList1.get(i).getType().equals("2"))
            {
                GalleryImagesModel galleryImagesModel = new GalleryImagesModel();
                galleryImagesModel.setType(uriList1.get(i).getType());
                galleryImagesModel.setFirst_image_id(uriList1.get(i).getImage_id());
                galleryImagesModel.setSecond_image_id("");
                galleryImagesModel.setFirst_image_name(uriList1.get(i).getImage());
                galleryImagesModel.setSecond_image_name("");
                galleryImagesModelList.add(galleryImagesModel);

            }else
                {
                    GalleryImagesModel galleryImagesModel = new GalleryImagesModel();
                    galleryImagesModel.setFirst_image_id(uriList1.get(i).getImage_id());
                    galleryImagesModel.setFirst_image_name(uriList1.get(i).getImage());
                    galleryImagesModel.setType(uriList1.get(i).getType());
                    if ((i+1)<uriList1.size()&& uriList1.get(i+1).getType().equals("1"))
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



           /* GalleryImagesModel galleryImagesModel = new GalleryImagesModel();
            galleryImagesModel.setFirst_image_id(uriList1.get(i).getImage_id());
            galleryImagesModel.setFirst_image_name(uriList1.get(i).getImage());
            galleryImagesModel.setType(uriList1.get(i).getType());
            if ((i+1)<uriList1.size())
            {
                galleryImagesModel.setSecond_image_id(uriList1.get(i+1).getImage_id());
                galleryImagesModel.setSecond_image_name(uriList1.get(i+1).getImage());

            }else
                {
                    galleryImagesModel.setSecond_image_id("");
                    galleryImagesModel.setSecond_image_name("");
                }

                galleryImagesModelList.add(galleryImagesModel);*/
        }
        AddFragments(galleryImagesModelList);


        //adapter.notifyDataSetChanged();


    }

    private void AddFragments(List<GalleryImagesModel> galleryImagesModelList) {


        for (GalleryImagesModel galleryImagesModel :galleryImagesModelList)
        {
            if (galleryImagesModel.getType().equals("0"))
            {
                fragmentList.add(FragmentDisplayCoverImage.newInstance(galleryImagesModel));
            }else if (galleryImagesModel.getType().equals("1"))
            {
                fragmentList.add(FragmentDisplayTwoImage.newInstance(galleryImagesModel));

            }else if (galleryImagesModel.getType().equals("2"))
            {
                fragmentList.add(FragmentDisplayOneImage.newInstance(galleryImagesModel));

            }


        }
        Log.e("frsssss",fragmentList.size()+"__");

        myViewPagerDisplayAdapter = new MyViewPagerDisplayAdapter(getSupportFragmentManager());
        myViewPagerDisplayAdapter.AddFragment(fragmentList);
        pager.setAdapter(myViewPagerDisplayAdapter);
        if (fragmentList.get(0) instanceof FragmentDisplayCoverImage)
        {
            counter.setText(getString(R.string.cov_photo));
        }else
            {
                counter.setText("(1/"+fragmentList.size()+")");
            }

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
