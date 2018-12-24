package com.Alatheer.Projects.lailaky.Activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.view.ViewGroup;
import android.widget.Button;
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

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalAlbumActivity extends AppCompatActivity {
    /*private RecyclerView recView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter adapter;*/
    private FinalAlbumImage instance;
    private List<Bitmap> finalImage_toUpload;
    private List<String> finalImageType_toUpload;

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
    private List<FinalImageModel>desiredList ;
    private int total_page=0;
    private TextView tv_page1,tv_page2;
    private FinalImageModel finalImageModel_after_update;
    private int pos_for_update;
    private Bitmap bitmap_cover=null;



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
        desiredList = new ArrayList<>();

        instance = FinalAlbumImage.getInstance();
        finalImage_toUpload = new ArrayList<>();
        finalImageType_toUpload = new ArrayList<>();

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
        tv_page1 = findViewById(R.id.tv_page1);
        tv_page2 = findViewById(R.id.tv_page2);


        if (!Locale.getDefault().getLanguage().equals("ar"))
        {
            back.setRotation(180f);
        }


        UpdateUI(instance.getImages());




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
                Log.e("sxsx",desiredList.size()+"__");
                PrepareDataToUpload(desiredList);
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

    private void UpdateUI(List<FinalImageModel> finalImageModelList) {
        if (instance.getCoverImage()==null)
        {
            image_add_cover.setVisibility(View.VISIBLE);
            image_cover_gallery.setVisibility(View.VISIBLE);
            bitmap_cover = null;
        }else
            {
                image_add_cover.setVisibility(View.VISIBLE);
                image_cover_gallery.setVisibility(View.GONE);
                bitmap_cover = instance.getCoverImage();
                image_cover.setImageBitmap(bitmap_cover);
            }


            orderPages(finalImageModelList);










    }

    private void orderPages(List<FinalImageModel> finalImageModelList) {

        Log.e("size",finalImageModelList.size()+"_");


        for (FinalImageModel finalImageModel:finalImageModelList)
        {
            Log.e("type_before_arrangement",finalImageModel.getType()+"_");
        }

        List<FinalImageModel> two_image_on_one_page_List = new ArrayList<>();
        List<FinalImageModel> one_image_on_one_page_List = new ArrayList<>();
        List<FinalImageModel> orderedList = new ArrayList<>();

        for (int i = 0; i<finalImageModelList.size();i++)
        {
            FinalImageModel finalImageModel = finalImageModelList.get(i);

            if (finalImageModel.getType().equals(Tags.type_two_pages))
            {
                two_image_on_one_page_List.add(finalImageModel);
            }else
                {
                    one_image_on_one_page_List.add(finalImageModel);
                }

        }

        if (two_image_on_one_page_List.size()>0)
        {
            desiredList.addAll(two_image_on_one_page_List);

        }

        if (one_image_on_one_page_List.size()>0)
        {
            orderedList.addAll(one_image_on_one_page_List);

        }

        for (FinalImageModel finalImageModel:orderedList)
        {
            Log.e("type_after_arrangement",finalImageModel.getType()+"_");
        }



        for (int i=0;i<orderedList.size();i+=2)
        {
            FinalImageModel finalImageModel = new FinalImageModel();
            finalImageModel.setType(orderedList.get(i).getType());
            finalImageModel.setFrame_type(orderedList.get(i).getFrame_type());
            finalImageModel.setPosition_on_frame(orderedList.get(i).getPosition_on_frame());

            finalImageModel.setImage1(orderedList.get(i).getImage1());


            if ((i+1) < orderedList.size())
            {
                finalImageModel.setImage2(orderedList.get(i+1).getImage1());
            }else
                {
                    finalImageModel.setImage2(null);

                }

                desiredList.add(finalImageModel);



        }

        for (FinalImageModel finalImageModel:desiredList)
        {
            Log.e("type_after_arrangement2",finalImageModel.getType()+"_");
        }

        tab.removeAllTabs();

            total_page = desiredList.size();

        for (int i=0 ;i<total_page;i++)
        {
            tab.addTab(tab.newTab());
        }

        for (int i=0 ;i<tab.getTabCount();i++)
        {
            View view = ((ViewGroup)tab.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();

            params.setMargins(0,0,8,0);
        }

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                recView.smoothScrollToPosition(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tv_page_counter.setText("("+1+"/"+total_page+")");

        updateAdapter(desiredList,total_page);





    }

    private void updateAdapter(List<FinalImageModel> desiredList, final int total_page)
    {
         adapter = new MyPagerAdapter(this,desiredList);
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
                        int page_prev = ((adapterPosition+1)*2)-1;
                        int page_next = (adapterPosition+1)*2;

                        tv_page1.setText("("+page_prev+")");
                        tv_page2.setText("("+page_next+")");

                        tab.getTabAt(adapterPosition).select();
                        tv_page_counter.setText("("+(adapterPosition+1)+"/"+total_page+")");

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
    public void deletePage(FinalImageModel finalImageModel ,String type,String pos_for_delete)
    {
        List<FinalImageModel> finalImageModelList = instance.deleteItem(finalImageModel,type,pos_for_delete);

        if (finalImageModelList.size()==0)
        {
            finish();
        }else
            {
                UpdatePageAfterDeleteItem(finalImageModelList);

            }

    }

    private void UpdatePageAfterDeleteItem(List<FinalImageModel> finalImageModelList)
    {
        this.desiredList.clear();
        this.total_page= 0;
        Log.e("size",finalImageModelList.size()+"_");


        for (FinalImageModel finalImageModel:finalImageModelList)
        {
            Log.e("type_before_arrangement",finalImageModel.getType()+"_");
        }

        List<FinalImageModel> two_image_on_one_page_List = new ArrayList<>();
        List<FinalImageModel> one_image_on_one_page_List = new ArrayList<>();
        List<FinalImageModel> orderedList = new ArrayList<>();
        List<FinalImageModel> desireList2 = new ArrayList<>();

        for (int i = 0; i<finalImageModelList.size();i++)
        {
            FinalImageModel finalImageModel = finalImageModelList.get(i);

            if (finalImageModel.getType().equals(Tags.type_two_pages))
            {
                two_image_on_one_page_List.add(finalImageModel);
            }else
            {
                one_image_on_one_page_List.add(finalImageModel);
            }

        }

        if (two_image_on_one_page_List.size()>0)
        {
            desireList2.addAll(two_image_on_one_page_List);

        }

        if (one_image_on_one_page_List.size()>0)
        {
            orderedList.addAll(one_image_on_one_page_List);

        }

        for (FinalImageModel finalImageModel:orderedList)
        {
            Log.e("type_after_arrangement",finalImageModel.getType()+"_");
        }



        for (int i=0;i<orderedList.size();i+=2)
        {
            FinalImageModel finalImageModel = new FinalImageModel();
            finalImageModel.setType(Tags.type_one_page);
            finalImageModel.setImage1(orderedList.get(i).getImage1());


            if ((i+1) < orderedList.size())
            {
                finalImageModel.setImage2(orderedList.get(i+1).getImage1());
            }else
            {
                finalImageModel.setImage2(null);

            }

            desireList2.add(finalImageModel);



        }

        for (FinalImageModel finalImageModel:desireList2)
        {
            Log.e("type_after_arrangement2",finalImageModel.getType()+"_");
        }

        tab.removeAllTabs();

        total_page = desireList2.size();

        for (int i=0 ;i<total_page;i++)
        {
            tab.addTab(tab.newTab());
        }

        for (int i=0 ;i<tab.getTabCount();i++)
        {
            View view = ((ViewGroup)tab.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();

            params.setMargins(0,0,15,0);
        }


        Log.e("total",total_page+"_2");

        tv_page_counter.setText("("+1+"/"+total_page+")");

        updateAdapter(desireList2,total_page);

    }

    public void NavigateToUpdateItem(FinalImageModel finalImageModel,String which_image,int pos_for_update)
    {
        this.pos_for_update = pos_for_update;
        Intent intent = new Intent(FinalAlbumActivity.this,UpdateImageActivity.class);
        intent.putExtra("frame",finalImageModel.getFrame_type());
        intent.putExtra("position",finalImageModel.getPosition_on_frame());
        intent.putExtra("which_image",which_image);
        instance.setItemBeforeUpdate(finalImageModel);
        startActivityForResult(intent,1122);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK && data!=null)
        {
            image_add_cover.setVisibility(View.VISIBLE);
            image_cover_gallery.setVisibility(View.GONE);
            bitmap_cover = instance.getCoverImage();
            image_cover.setImageBitmap(bitmap_cover);
        }
        else if (requestCode == 1122 && resultCode == RESULT_OK && data!=null)
        {
           finalImageModel_after_update = instance.getFinalImageModel_after_update();
           this.desiredList.set(pos_for_update,finalImageModel_after_update);
           adapter.notifyDataSetChanged();

        }
    }

    private void PrepareDataToUpload(List<FinalImageModel> desiredList)
    {
        //collect all image and types
        finalImage_toUpload.clear();
        finalImageType_toUpload.clear();

        if (bitmap_cover!=null)
        {
            finalImageType_toUpload.add(Tags.type_cover);
            finalImage_toUpload.add(bitmap_cover);
        }

        for (int i=0;i<desiredList.size();i++)
        {
            Log.e(i+"",desiredList.get(i).getImage1()+"__");
            Log.e(i+"",desiredList.get(i).getImage2()+"__");

        }


        for (FinalImageModel finalImageModel :desiredList)
        {

            if (finalImageModel.getImage1()!=null && finalImageModel.getImage2()!=null)
            {
                Bitmap bitmap1 = BitmapFactory.decodeByteArray(finalImageModel.getImage1(),0,finalImageModel.getImage1().length);
                finalImage_toUpload.add(bitmap1);
                finalImageType_toUpload.add(finalImageModel.getType());

                Bitmap bitmap2 = BitmapFactory.decodeByteArray(finalImageModel.getImage2(),0,finalImageModel.getImage2().length);
                finalImage_toUpload.add(bitmap2);
                finalImageType_toUpload.add(finalImageModel.getType());

            }else if (finalImageModel.getImage1()!=null)
            {
                Bitmap bitmap = BitmapFactory.decodeByteArray(finalImageModel.getImage1(),0,finalImageModel.getImage1().length);
                finalImage_toUpload.add(bitmap);
                finalImageType_toUpload.add(finalImageModel.getType());
            }else if (finalImageModel.getImage2()!=null)
            {
                Bitmap bitmap = BitmapFactory.decodeByteArray(finalImageModel.getImage2(),0,finalImageModel.getImage2().length);
                finalImage_toUpload.add(bitmap);
                finalImageType_toUpload.add(finalImageModel.getType());

            }
        }

        Upload(finalImage_toUpload,finalImageType_toUpload);


    }
    private void Upload(List<Bitmap> finalImage_toUpload,List<String> finalImageType_toUpload) {




        /*List<MultipartBody.Part> partList = new ArrayList<>();

        List<RequestBody> requestBodyList = new ArrayList<>();
        for (Bitmap bitmap : finalImage_toUpload)
        {
            File file = getFile(resizeBitmap(bitmap));
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("images[]",file.getName(),requestBody);
            partList.add(part);

        }


        for (String type: finalImageType_toUpload)
        {
            RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"),type);
            requestBodyList.add(requestBody);
        }
*/




        //SendDataToServer(partList,requestBodyList);
    }

    private void SendDataToServer(List<MultipartBody.Part> partList, List<RequestBody> requestBodyList) {
        dialog.show();
        Api.getClient().create(Services.class)
                .uploadAlbumImages(user_id,offer_id,paper_id,requestBodyList,partList)
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

                                Intent intent =new Intent(FinalAlbumActivity.this,Credit.class);
                                startActivity(intent);
                                finish();


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


   /* public void setBitmapForDelete(int pos,View view)
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
    }*/

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
