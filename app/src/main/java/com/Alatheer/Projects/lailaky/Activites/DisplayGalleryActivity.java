package com.Alatheer.Projects.lailaky.Activites;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.Alatheer.Projects.lailaky.Adapter.Display_galleryAdapter;
import com.Alatheer.Projects.lailaky.ApiServices.Api;
import com.Alatheer.Projects.lailaky.ApiServices.Services;
import com.Alatheer.Projects.lailaky.Models.UserModel;
import com.Alatheer.Projects.lailaky.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayGalleryActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private List<Bitmap> galleryModelList;
    private Toolbar toolBar;
    private Button uploadBtn;
    private final int IMG_REQ=1245;
    private List<Bitmap> selected_image;
    private List<Bitmap> deleted;
    public boolean is_normal = false;
    private int album_size;
    private List<Bitmap> bitmapList;
    private List<String> encodedImages;
    private String user_id,offer_id;
    private ProgressDialog dialog;
    private boolean isGranted=false;
    private String write_external = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private String mng_gocument = Manifest.permission.MANAGE_DOCUMENTS;

    private LinearLayout container;
    private final int per_REQ = 12012;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_gallery);
        initView();
        getDataFromIntent();
        CreatePorgDialog();
        checkPermission();
    }
    private void initView()
    {

        toolBar = findViewById(R.id.toolBar);
        uploadBtn = findViewById(R.id.uploadBtn);
        container = findViewById(R.id.container);
        setSupportActionBar(toolBar);
        recView = findViewById(R.id.recView);
        manager = new GridLayoutManager(this,2);
        recView.setLayoutManager(manager);
        galleryModelList = new ArrayList<>();
        galleryModelList.clear();
        adapter = new Display_galleryAdapter(this,galleryModelList);
        recView.setAdapter(adapter);
        selected_image = new ArrayList<>();
        deleted = new ArrayList<>();
        bitmapList = new ArrayList<>();
        encodedImages = new ArrayList<>();
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (galleryModelList.size()>0)
                {
                    if (galleryModelList.size()>album_size)
                    {
                        Toast.makeText(DisplayGalleryActivity.this, "حجم الالبوم اقل من عدد الصور", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        UploadImages(encodedImages);
                    }
                }else
                    {
                        Toast.makeText(DisplayGalleryActivity.this, "عفوا برجاء اختيار صور الالبوم", Toast.LENGTH_SHORT).show();

                    }

            }
        });


    }
    private void getDataFromIntent()
    {
        Intent intent = getIntent();
        if (intent!=null)
        {
           /* galleryModels = intent.getStringArrayListExtra("data");
            List<Bitmap> bitmaps = new ArrayList<>();
            for (String s:galleryModels)
            {
                byte[] bytes = Base64.decode(s, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                bitmaps.add(bitmap);
            }

            galleryModelList.addAll(bitmaps);

            adapter.notifyDataSetChanged();
*/
            album_size = Integer.parseInt(intent.getStringExtra("album_size"));

            user_id = intent.getStringExtra("user_id");
            offer_id = intent.getStringExtra("id_offer");
        }
    }
    private void checkPermission()
    {
        String [] perms = new String[]{write_external,mng_gocument};

        if (ContextCompat.checkSelfPermission(this,write_external)== PackageManager.PERMISSION_GRANTED)
        {
            if (ContextCompat.checkSelfPermission(this,mng_gocument)== PackageManager.PERMISSION_GRANTED)
            {
                isGranted = true;

            }else
                {
                    ActivityCompat.requestPermissions(this,perms,per_REQ);

                }
        }else
            {
                ActivityCompat.requestPermissions(this,perms,per_REQ);
            }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        isGranted = false;
        if (requestCode == per_REQ)
        {
            if (grantResults.length>0)
            {
                for (int i = 0;i<grantResults.length;i++)
                {
                    if (grantResults[i]!=PackageManager.PERMISSION_GRANTED)
                    {
                        isGranted = false;
                        return;
                    }
                }
                isGranted = true;
            }
        }
    }

    private void CreatePorgDialog()
    {
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.upload_img));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
    }
    private void UploadImages(List<String> encodedImages)
    {


        //encodedImages.clear();
        Log.e("bitmap size",""+encodedImages.size());

        encodeImages(encodedImages);





    }

    private void encodeImages(List<String> encodedImages)
    {

        Log.e("uploadedsize",encodedImages.size()+"");
        for (String s:encodedImages)
        {
            Log.e("encodedimage",s);
        }

        UploadDataToServer(encodedImages,user_id,offer_id);



    }

    private void UploadDataToServer(List<String> encodedImages, String user_id, String offer_id) {


        dialog.show();
        Services services= Api.getClient().create(Services.class);
        Call<UserModel> call =services.
                BookAlbum(encodedImages,user_id,offer_id);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                if (response.isSuccessful())
                {
                    if (response.body().getSuccess()==1)
                    {
                        dialog.dismiss();
                        Toast.makeText(DisplayGalleryActivity.this, R.string.album_reg, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                    {
                        dialog.dismiss();

                        Toast.makeText(DisplayGalleryActivity.this, R.string.album_not_booked, Toast.LENGTH_SHORT).show();

                    }

                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(DisplayGalleryActivity.this, getString(R.string.something), Toast.LENGTH_SHORT).show();
                Log.e("error", t.getMessage());


            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.delete)
        {
            //selected_image.removeAll(deleted);
            galleryModelList.removeAll(deleted);
            if (galleryModelList.size()>0)
            {
                uploadBtn.setVisibility(View.VISIBLE);
                container.setVisibility(View.GONE);
            }else if (galleryModelList.size()==0)
            {
                uploadBtn.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
            }
            Log.e("selected size ",""+selected_image.size());
            is_normal = true;
            adapter.notifyDataSetChanged();
            Display_galleryAdapter galleryAdapter = (Display_galleryAdapter) recView.getAdapter();
            galleryAdapter.UpdateAdapter(deleted);
            galleryAdapter.clearList();
            toolBar.getMenu().clear();
            toolBar.inflateMenu(R.menu.add_menu);
        }else if (id ==R.id.add)
        {
            selectImages();
        }
        return super.onOptionsItemSelected(item);
    }

    private void selectImages() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        startActivityForResult(intent.createChooser(intent,"select image"),IMG_REQ);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQ && resultCode==RESULT_OK && data!=null)
        {

            ClipData clipData = data.getClipData();
            if (clipData==null)
            {
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                    selected_image.add(bitmap);
                    uploadBtn.setVisibility(View.VISIBLE);
                    container.setVisibility(View.GONE);
                    //galleryModelList.addAll(selected_image);
                    encodedImage1(bitmap);
                    UpdateAdapter(selected_image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }else
                {
                    if (clipData.getItemCount()>0)
                    {
                        for (int i=0;i<clipData.getItemCount();i++)
                        {
                            ClipData.Item item = clipData.getItemAt(i);
                            Uri uri = item.getUri();
                            try {
                                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                                selected_image.add(bitmap);
                                encodedImage1(bitmap);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }


                        }
                        //galleryModelList.addAll(galleryModelList);
                        uploadBtn.setVisibility(View.VISIBLE);
                        container.setVisibility(View.GONE);
                        UpdateAdapter(selected_image);
                    }
                }
        }
    }


    private void UpdateAdapter(List<Bitmap> selImg)
    {
        galleryModelList.addAll(selImg);
        adapter.notifyDataSetChanged();
        selected_image.clear();
    }
    public void UpdateToolBar(List<Bitmap> deleted,int pos)
    {
        encodedImages.remove(pos);
        this.deleted = deleted;
        Log.e("deleted size",""+this.deleted.size());
        if (deleted.size()>0)
        {
            toolBar.getMenu().clear();
            toolBar.inflateMenu(R.menu.delete_menu);
        }else
            {
                toolBar.getMenu().clear();
            }
    }

    private void encodedImage1(Bitmap bitmap)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,90,outputStream);
        byte[] bytes = outputStream.toByteArray();
        encodedImages.add(Base64.encodeToString(bytes,Base64.DEFAULT));
    }
    @Override
    public void onClick(View view) {

    }
}
