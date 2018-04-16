package com.Alatheer.Projects.laylaky.Activites;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
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
import com.Alatheer.Projects.laylaky.Models.ResponseModel;
import com.Alatheer.Projects.laylaky.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.Alatheer.Projects.laylaky.ApiServices.Tags.ImgPath;

public class DetailsAlbumaty extends AppCompatActivity implements View.OnLongClickListener{
    private RecyclerView recView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter adapter;
    private ProgressBar bar;
    private Toolbar toolBar;
    private int  album_size;
    public boolean isContextMode=false;
    Gallery simpleGallery;
    CustomGalleryAdapter customGalleryAdapter;
    ImageView selectedImageView;
    private String album_id;
     List<ImgModel> uriList;
     ImgModel imgModel;
     TextView counter;
     private int count=0;
     private List<ImgModel> selectedImagesList;
     private final int IMG_REQ=1230;
     private Bitmap bitmap;
     private List<String> encodedImageList;
     private List<Bitmap> bitmaps;
     private ProgressDialog dialog,dialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_albumaty);

        initView();
        getDataFromIntent();
        CreateProgress();
        CreatedeleteProgress();
        final SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recView);
        recView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                    int currPos = snapHelper.findTargetSnapPosition(recView.getLayoutManager(), dx, dy)+1;
                    int total = recView.getAdapter().getItemCount();

                    if (!isContextMode)
                    {
                        if (currPos>total)
                        {
                            currPos=total;
                            counter.setText("<"+currPos+"/"+total+">");

                        }else
                            {
                                counter.setText("<"+currPos+"/"+total+">");

                            }

                    }


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

    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }

    private void initView() {
        selectedImagesList = new ArrayList<>();
        uriList=new ArrayList<>();
        encodedImageList = new ArrayList<>();

        bar = findViewById(R.id.progBar);
        bar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this,R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        recView = findViewById(R.id.recView);
        manager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recView.setLayoutManager(manager);
        adapter = new GalleryAdapter(uriList,this);
        recView.setAdapter(adapter);
        counter = findViewById(R.id.counter);
        toolBar = findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void CreateProgress()
    {
        ProgressBar bar = new ProgressBar(this);
        Drawable drawable = bar.getIndeterminateDrawable().mutate();
        drawable.setColorFilter(ContextCompat.getColor(this,R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        dialog = new ProgressDialog(this);
        dialog.setMessage("جار اضافة الصور..");
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
        dialog2.setMessage("جار حزف الصور..");
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
                album_id = intent.getStringExtra("id_album");
                album_size = intent.getIntExtra("album_size",0);
            }
        }
    }


    @Override
    public boolean onLongClick(View view) {
        isContextMode = true;
        toolBar.getMenu().clear();
        toolBar.inflateMenu(R.menu.delete_menu);
        adapter.notifyDataSetChanged();
        counter.setText("0 image selected");
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.add)
        {

            if ( recView.getAdapter().getItemCount()== album_size)
            {
                Toast.makeText(this, "البوم الصور ممتلئ", Toast.LENGTH_SHORT).show();
            }else if (recView.getAdapter().getItemCount()<album_size)
            {
                selectImages();

            }
        }else if (id==R.id.delete)
        {
            Delete_Image(selectedImagesList);

        }
        return super.onOptionsItemSelected(item);
    }

    private void Delete_Image(final List<ImgModel> selectedImagesList) {

        List<String> ids = new ArrayList<>();
        for (ImgModel imgModel :selectedImagesList)
        {
            ids.add(imgModel.getImage_id());
        }
        dialog2.show();
        Retrofit retrofit = Api.getClient();
        Services services = retrofit.create(Services.class);
        Call<ResponseModel> call = services.deleteImages(ids);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful())
                {
                    if (response.body().getSuccess()==1)
                    {
                        GalleryAdapter galleryAdapter = (GalleryAdapter) recView.getAdapter();
                        galleryAdapter.DeleteImages(selectedImagesList);
                        isContextMode = false;
                        adapter.notifyDataSetChanged();
                        counter.setText("البوم الصور");
                        toolBar.getMenu().clear();
                        toolBar.inflateMenu(R.menu.add_menu);
                        count=0;
                        dialog2.dismiss();
                        Toast.makeText(DetailsAlbumaty.this, "تم حزف الصور بنجاح", Toast.LENGTH_SHORT).show();
                    }else
                        {
                            dialog2.dismiss();

                            Toast.makeText(DetailsAlbumaty.this, "خطأ لم يتم حزف الصور حاول مره اخرى لاحقا", Toast.LENGTH_SHORT).show();
                        }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                dialog2.dismiss();

                Toast.makeText(DetailsAlbumaty.this, "Something went haywire", Toast.LENGTH_SHORT).show();
                Log.e("Error",t.getMessage());
            }
        });
    }

    private void selectImages() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(intent.createChooser(intent,"Select image"),IMG_REQ);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==IMG_REQ && resultCode == RESULT_OK && data !=null)
        {
            ClipData clipData = data.getClipData();
            bitmaps = new ArrayList<>();

            if (clipData == null)
            {
                Uri uri = data.getData();
                try {
                    bitmaps.clear();
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                    bitmaps.add(bitmap);
                    int total_size = recView.getAdapter().getItemCount()+bitmaps.size();


                    if (total_size>album_size)
                   {
                       Toast.makeText(this, "عدد صور الالبوم محدوده", Toast.LENGTH_SHORT).show();
                   }else if (total_size<=album_size)
                   {
                       encodeImage1(bitmap);

                   }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }else
                {
                    bitmaps.clear();
                    if (clipData.getItemCount()>0)
                    {

                        List<Uri> uriList = new ArrayList<>();
                        for (int i=0;i<clipData.getItemCount();i++)
                        {
                            ClipData.Item item = clipData.getItemAt(i);
                            uriList.add(item.getUri());

                        }




                        if (uriList.size()>0)
                        {
                            for (Uri uri :uriList)
                            {
                                try {
                                    Bitmap bitmap2 = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                                    bitmaps.add(bitmap2);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                            int total_size = recView.getAdapter().getItemCount()+clipData.getItemCount();

                              if (total_size>album_size)
                            {
                                Toast.makeText(this, "عدد صور الالبوم محدوده", Toast.LENGTH_SHORT).show();
                            }else if (total_size<=album_size)
                            {
                                enCodeImage(bitmaps);

                            }

                        }
                    }

                }
        }
    }

    private void enCodeImage(List<Bitmap> bitmapList)
    {

        //encodedImageList.clear();
        for (Bitmap bitmap:bitmapList)
        {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,90,outputStream);
            byte [] bytes = outputStream.toByteArray();
            encodedImageList.add(Base64.encodeToString(bytes,Base64.DEFAULT));
        }

        AddImages(encodedImageList);
    }

    private void encodeImage1(Bitmap bitmap)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,90,outputStream);
        byte [] bytes = outputStream.toByteArray();
        encodedImageList.add(Base64.encodeToString(bytes,Base64.DEFAULT));
        AddImages(encodedImageList);

    }

    private void AddImages(final List<String> encodedImageList) {

        dialog.show();
        Retrofit retrofit = Api.getClient();
        Services services = retrofit.create(Services.class);
        Call<List<ImgModel>> call = services.AddImages(album_id, encodedImageList);
        call.enqueue(new Callback<List<ImgModel>>() {
            @Override
            public void onResponse(Call<List<ImgModel>> call, Response<List<ImgModel>> response) {
                if (response.isSuccessful())
                {
                    if (response.body().size()>0)
                    {
                        GalleryAdapter galleryAdapter = (GalleryAdapter) recView.getAdapter();
                        galleryAdapter.AddImage(response.body());
                        dialog.dismiss();
                        encodedImageList.clear();
                        Toast.makeText(DetailsAlbumaty.this, "تم إضافة الصور بنجاح", Toast.LENGTH_SHORT).show();
                    }else
                        {
                            dialog.dismiss();
                            Toast.makeText(DetailsAlbumaty.this, "عفوا لم يتم إضافة الصور", Toast.LENGTH_SHORT).show();

                        }
                }
            }

            @Override
            public void onFailure(Call<List<ImgModel>> call, Throwable t) {
                dialog.dismiss();

                Toast.makeText(DetailsAlbumaty.this, "Something went haywire", Toast.LENGTH_SHORT).show();
                Log.e("Error",t.getMessage());
            }
        });

    }

    public void SetPos(View view , int pos)
    {
        if(((CheckBox)view).isChecked())
        {
            count++;
            IncreaseAndDecreaseCounter(count);
            selectedImagesList.add(uriList.get(pos));
        }else
            {

                count--;
                IncreaseAndDecreaseCounter(count);
                selectedImagesList.remove(uriList.get(pos));


            }
    }

    private void IncreaseAndDecreaseCounter(int counte)
    {
        if (counte==0)
        {
            counter.setText("0 image selected");

        }else if (counte>0)
        {
            counter.setText(counte+" image selected");

        }else if (counte<0)
        {
            count=0;
            counter.setText("0 image selected");

        }
    }

    @Override
    public void onBackPressed() {
        if (isContextMode)
        {
            isContextMode=false;
            adapter.notifyDataSetChanged();
            toolBar.getMenu().clear();
            toolBar.inflateMenu(R.menu.add_menu);
            counter.setText("البوم الصور");
            count=0;
            selectedImagesList.clear();
        }else
            {
                super.onBackPressed();

            }

    }
}
