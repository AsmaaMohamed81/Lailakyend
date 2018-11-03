package com.Alatheer.Projects.lailaky.Activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.Alatheer.Projects.lailaky.Adapter.FinalAlbumAdapter;
import com.Alatheer.Projects.lailaky.ApiServices.Api;
import com.Alatheer.Projects.lailaky.ApiServices.Services;
import com.Alatheer.Projects.lailaky.Models.ResponseModel;
import com.Alatheer.Projects.lailaky.R;
import com.Alatheer.Projects.lailaky.SingleTone.FinalAlbumImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalAlbumActivity extends AppCompatActivity {
    private List<Bitmap> bitmapList;
    private RecyclerView recView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter adapter;
    private FinalAlbumImage instance;
    private List<Bitmap> bitmapList_selection;
    private Toolbar toolBar;
    private ImageView back;
    private String user_id="",offer_id="";
    private Button uploadBtn;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_album);
        initView();
        CreateProgress();
    }

    private void CreateProgress() {
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.upl_image));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);

    }


    private void initView() {
        getDataFromIntent();
        instance = FinalAlbumImage.getInstance();
        bitmapList = new ArrayList<>();
        bitmapList_selection = new ArrayList<>();
        toolBar = findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        back = findViewById(R.id.back);
        uploadBtn = findViewById(R.id.uploadBtn);
        recView = findViewById(R.id.recView);
        manager = new LinearLayoutManager(this);
        recView.setLayoutManager(manager);
        adapter = new FinalAlbumAdapter(this,bitmapList);
        recView.setAdapter(adapter);

        bitmapList.addAll(instance.getbitmaps());
        adapter.notifyDataSetChanged();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Upload();
            }
        });
    }

    private void Upload() {

        List<MultipartBody.Part> partList = new ArrayList<>();
        for (Bitmap bitmap :bitmapList)
        {
            File file = getFile(resizeBitmap(bitmap));
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("images[]",file.getName(),requestBody);
            partList.add(part);

        }

        SendDataToServer(partList);
    }

    private void SendDataToServer(List<MultipartBody.Part> partList) {
        dialog.show();
        Api.getClient().create(Services.class)
                .uploadAlbumImages(user_id,offer_id,partList)
                .enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        if (response.isSuccessful())
                        {
                            dialog.dismiss();
                            if (response.body().getSuccess_upload()==1)
                            {
                                ClearData();
                                Toast.makeText(FinalAlbumActivity.this, "تم رفع صور الالبوم بنجاح", Toast.LENGTH_SHORT).show();
                            }else if (response.body().getSuccess_upload()==0)
                            {
                                Toast.makeText(FinalAlbumActivity.this, "فشل لم يتم رفع الالبوم حاول مره اخرى لاحقا", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Log.e("Error",t.getMessage());
                        Toast.makeText(FinalAlbumActivity.this, "فشل لم يتم رفع الالبوم حاول مره اخرى لاحقا", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
    }

    private void ClearData() {
        instance.ClearList();
        finish();

    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent!=null)
        {

            user_id = intent.getStringExtra("user_id");
            offer_id =intent.getStringExtra("id_offer");

        }
    }


    public void setBitmapForDelete(int pos,View view)
    {
        CheckBox checkBox = (CheckBox) view;
        if (checkBox.isChecked())
        {
            bitmapList_selection.add(bitmapList.get(pos));
            updateToolBar(1);
        }else
            {

                bitmapList_selection.remove(bitmapList.get(pos));
                if (bitmapList_selection.size()==0)
                {
                    uploadBtn.setVisibility(View.INVISIBLE);
                    updateToolBar(0);


                }
            }
    }

    private void updateToolBar(int type)
    {
        if (type==0)
        {
            toolBar.getMenu().clear();
            //clear
        }else if (type ==1)
        {
            toolBar.getMenu().clear();
            toolBar.inflateMenu(R.menu.delete_menu);
            //inflate
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.delete)
        {
            Log.e("deletedbitmap",bitmapList_selection.size()+"");
            bitmapList.removeAll(bitmapList_selection);
            adapter.notifyDataSetChanged();
            instance.deleteItem(bitmapList_selection);
            instance.setCount(instance.getCount()-bitmapList_selection.size());
            bitmapList_selection.clear();
            if (bitmapList.size()==0)
            {
                finish();

            }
        }
        return true;
    }


    private File getFile(Bitmap bitmap)
    {
        File file = new File(getCacheDir(),System.currentTimeMillis()+"file.png");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,outputStream);
        byte [] bytes = outputStream.toByteArray();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private Bitmap resizeBitmap(Bitmap bitmap)
    {
        int maxHeight = 2000;
        int maxWidth = 2000;
        float scale = Math.min(((float)maxHeight / bitmap.getWidth()), ((float)maxWidth / bitmap.getHeight()));

        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return bitmap;
    }
    //images[]
}
