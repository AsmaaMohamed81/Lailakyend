package com.Alatheer.Projects.lailaky.Activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Alatheer.Projects.lailaky.Adapter.MyPagerAdapter;
import com.Alatheer.Projects.lailaky.ApiServices.Api;
import com.Alatheer.Projects.lailaky.ApiServices.Services;
import com.Alatheer.Projects.lailaky.ApiServices.Tags;
import com.Alatheer.Projects.lailaky.Models.FinalImageModel;
import com.Alatheer.Projects.lailaky.Models.ResponseModel;
import com.Alatheer.Projects.lailaky.R;
import com.Alatheer.Projects.lailaky.SingleTone.FinalAlbumImage;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalAlbumActivity extends AppCompatActivity {
    private List<Bitmap> bitmapList;
    private List<String> typesList;
    /*private RecyclerView recView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter adapter;*/
    private FinalAlbumImage instance;
    private List<Bitmap> bitmapList_selection;
    private Toolbar toolBar;
    private ImageView back;
    private String user_id="",offer_id="",paper_id="";
    private Button uploadBtn;
    private ProgressDialog dialog;
    private ImageView image_cover_gallery,image_cover,image_add_cover;
    private TabLayout tab;
    private TextView tv_page_counter;
    private DiscreteScrollView recView;
    private MyPagerAdapter adapter;
    private List<FinalImageModel> finalImageModelList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_album);
        initView();
        //Toast.makeText(FinalAlbumActivity.this, ""+paper_id, Toast.LENGTH_SHORT).show();

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
        finalImageModelList = new ArrayList<>();
        instance = FinalAlbumImage.getInstance();
        bitmapList = new ArrayList<>();
        typesList = new ArrayList<>();
        bitmapList_selection = new ArrayList<>();
        toolBar = findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        back = findViewById(R.id.back);
        uploadBtn = findViewById(R.id.uploadBtn);
        image_cover_gallery = findViewById(R.id.image_cover_gallery);
        image_cover = findViewById(R.id.image_cover);
        image_add_cover = findViewById(R.id.image_add_cover);
        recView = findViewById(R.id.recView);

        tab = findViewById(R.id.tab);
        tv_page_counter = findViewById(R.id.tv_page_counter);

        /*recView = findViewById(R.id.recView);
        manager = new LinearLayoutManager(this);
        recView.setLayoutManager(manager);
        adapter = new FinalAlbumAdapter(this,bitmapList);
        recView.setAdapter(adapter);*/

        if (!Locale.getDefault().getLanguage().equals("ar"))
        {
            back.setRotation(180f);
        }

        typesList.addAll(instance.getImageTypeList());
        bitmapList.addAll(instance.getBitmaps());

        UpdateUI(bitmapList,typesList);




        //adapter.notifyDataSetChanged();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Upload(bitmapList,typesList);
            }
        });

        image_add_cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalAlbumActivity.this,makeCoverActivity.class);

                intent.putExtra("album_size","0");
                intent.putExtra("id_offer",offer_id);
                intent.putExtra("user_id",user_id);
                intent.putExtra("paper_id",paper_id);
                intent.putExtra("from","1");
                startActivityForResult(intent,123);
            }
        });


    }

    private void UpdateUI(final List<Bitmap> bitmapList, List<String> typesList) {
        if (instance.getCoverImage()==null)
        {
            image_add_cover.setVisibility(View.VISIBLE);
            image_cover_gallery.setVisibility(View.VISIBLE);
        }else
            {
                image_add_cover.setVisibility(View.VISIBLE);
                image_cover_gallery.setVisibility(View.GONE);
                image_cover.setImageBitmap(instance.getCoverImage());
            }

            /*int counter =0;
            for (String type:typesList)
            {
                if (type.equals(Tags.type_two_pages))
                {
                    counter +=1;
                }
            }*/



            //Log.e("counter",counter+"_");
        //tv_page_counter.setText("("+1+"/"+(bitmapList.size()-counter)+")");



        List<Integer> two_image_indexes = new ArrayList<>();

        for (int i = 0 ; i < typesList.size(); i++)
        {
            if (typesList.get(i).equals(Tags.type_two_pages))
            {
                two_image_indexes.add(i);
            }
        }



        List<Bitmap> two_image_bitmap = new ArrayList<>();

        if (two_image_indexes.size()>0)
        {
            for (int i = 0 ; i < two_image_indexes.size(); i++)
            {
                typesList.remove(i);
                two_image_bitmap.add(bitmapList.get(i));
                bitmapList.remove(i);
            }

        }


        if (two_image_indexes.size()>0)
        {
            for (int i = 0 ; i < two_image_indexes.size(); i++)
            {
                typesList.add(i,Tags.type_two_pages);
                bitmapList.add(i,two_image_bitmap.get(i));
            }

        }



        for (int i = 0 ;i<bitmapList.size();i+=2)
        {
            FinalImageModel finalImageModel = new FinalImageModel();
            if (typesList.get(i).equals(Tags.type_two_pages))
            {
                finalImageModel.setImage1(getByteArrayFromBitmap(bitmapList.get(i)));
                finalImageModel.setType(Tags.type_two_pages);
                finalImageModel.setImage2(null);

            }else if (typesList.get(i).equals(Tags.type_one_page))
            {
                finalImageModel.setImage1(getByteArrayFromBitmap(bitmapList.get(i)));
                finalImageModel.setType(Tags.type_one_page);
            }

            i++;

            if (i<bitmapList.size())
            {
                finalImageModel.setImage1(getByteArrayFromBitmap(bitmapList.get(i)));
                finalImageModel.setType(Tags.type_one_page);
            }

            finalImageModelList.add(finalImageModel);

        }



        for (int i=0;i<(bitmapList.size());i++)
        {
            tab.addTab(tab.newTab());
        }









        adapter = new MyPagerAdapter(this,finalImageModelList);
        recView.setAdapter(adapter);

        recView.setItemTransformer(new ScaleTransformer.Builder()
                .setMaxScale(0.97f)
                .setMinScale(0.97f)
                .setPivotX(Pivot.X.CENTER)
                .setPivotY(Pivot.Y.BOTTOM)
                .build());


        try {
            recView.addOnItemChangedListener(new DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>() {
                @Override
                public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
                    try {
                        tab.getTabAt(adapterPosition).select();
                        tv_page_counter.setText("("+(adapterPosition+1)+"/"+bitmapList.size()+")");

                    }catch (Exception e){}

                }
            });
        }catch (Exception e)
        {

        }



    }

    private byte [] getByteArrayFromBitmap(Bitmap bitmap)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        return outputStream.toByteArray();
    }
    public void deletePage(int pos)
    {
        bitmapList.remove(pos);
        typesList.remove(pos);
        instance.deleteItem(pos);


        if (bitmapList.size()==0)
        {
            finish();
        }else
            {

                tab.removeTabAt(pos);
                adapter.notifyItemRemoved(pos);

                //myPagerAgapter.AddBitmapList(bitmapList);
            }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK && data!=null)
        {
            image_add_cover.setVisibility(View.VISIBLE);
            image_cover_gallery.setVisibility(View.GONE);
            image_cover.setImageBitmap(instance.getCoverImage());
        }
    }

    private void Upload(List<Bitmap> bitmapList, List<String> typesList) {
        if (instance.getCoverImage()!=null)
        {
            bitmapList.add(0,instance.getCoverImage());
            typesList.add(0, Tags.type_cover);
        }

        Log.e("bitmap_size",bitmapList.size()+"bs");
        Log.e("type_size",typesList.size()+"ts");

        int count = 0;
        for (String type:typesList)
        {
            count += Integer.parseInt(type);
            Log.e("type0",count+"");
        }




        List<MultipartBody.Part> partList = new ArrayList<>();
        List<RequestBody> requestBodyList = new ArrayList<>();
        for (Bitmap bitmap : bitmapList)
        {
            File file = getFile(resizeBitmap(bitmap));
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("images[]",file.getName(),requestBody);
            partList.add(part);

        }


        for (String type: typesList)
        {
            RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"),type);
            requestBodyList.add(requestBody);
        }


        Log.e("bitmap_size",bitmapList.size()+"bs");



        //SendDataToServer(partList,requestBodyList);
    }

    private void SendDataToServer(List<MultipartBody.Part> partList, List<RequestBody> requestBodyList) {
        dialog.show();
        Api.getClient().create(Services.class)
                .uploadAlbumImages(user_id,offer_id,paper_id,partList)
                .enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        if (response.isSuccessful())
                        {
                            dialog.dismiss();
                            if (response.body().getSuccess_upload()==1)
                            {
                         //   ClearData();


                                Toast.makeText(FinalAlbumActivity.this, "تم رفع صور الالبوم بنجاح", Toast.LENGTH_SHORT).show();

                                Intent intent =new Intent(FinalAlbumActivity.this,Credit.class);
                                startActivity(intent);


                            }else if (response.body().getSuccess_upload()==0)
                            {
                                Toast.makeText(FinalAlbumActivity.this, "فشل لم يتم رفع الالبوم حاول مره اخرى لاحقا", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Log.e("Erroru",t.getMessage());
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
            paper_id =intent.getStringExtra("paper_id");


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
           /* Log.e("deletedbitmap",bitmapList_selection.size()+"");
            bitmapList.removeAll(bitmapList_selection);
            //adapter.notifyDataSetChanged();
            instance.deleteItem(bitmapList_selection);
            instance.setCount(instance.getCount()-bitmapList_selection.size());
            bitmapList_selection.clear();
            if (bitmapList.size()==0)
            {
                finish();

            }*/
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
