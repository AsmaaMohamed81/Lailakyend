package com.Alatheer.Projects.laylaky.Activites;

import android.bluetooth.BluetoothClass;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Alatheer.Projects.laylaky.ApiServices.Api;
import com.Alatheer.Projects.laylaky.ApiServices.Services;
import com.Alatheer.Projects.laylaky.Models.UserModel;
import com.Alatheer.Projects.laylaky.R;
import com.Alatheer.Projects.laylaky.SingleTone.Users;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailOffer extends AppCompatActivity implements Users.onCompleteListener {
    TextView title, desc, price;
    ImageView img;
    Button detail_book,Upload;
    Users users;
    String titlee, desce, pricee, imgg, idoffer;
    int IMG_REQ = 200;

    List<Uri> uriList;
    List<Uri> selectedImage;
    List<String> enCodedImageList;

    UserModel userModel;
    String user_type;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_offer);
        initView();
        getDataFromIntent();

        enCodedImageList = new ArrayList<>();

        uriList = new ArrayList<>();
        selectedImage = new ArrayList<>();

        users = Users.getInstance();
        users.getData(this);




        title.setText(titlee);
        desc.setText(desce);
        price.setText(pricee);

        Picasso.with(this).load(imgg).into(img);



    }



    private void book(){


        Services services= Api.getClient().create(Services.class);
        Call<UserModel> call =services.BookAlbum(enCodedImageList,userModel.getUser_id(),idoffer);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                if (response.isSuccessful())
                {
                    if (response.body().getSuccess()==1)
                    {
                Toast.makeText(DetailOffer.this, "Add", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(DetailOffer.this, "Error", Toast.LENGTH_SHORT).show();

                    }

                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.e("error", t.getMessage());


            }
        });


    }




    private void initView() {

        title = findViewById(R.id.detail_title);
        desc = findViewById(R.id.detail_desc);
        price = findViewById(R.id.detail_pric);
        detail_book = findViewById(R.id.detail_book);
        Upload=findViewById(R.id.Upload);

        img = findViewById(R.id.detail_img);
        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("idhhhhhhhhhhhhhhhhhhhh",userModel.getUser_id());
                Log.d("id",idoffer);


                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(intent, IMG_REQ);

                // Log.e("imaaaaaaaaaaaaaaaaage", enCodedImageList.get(1));





            }
        });

        detail_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                book();


            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQ && resultCode == RESULT_OK && data != null) {
            ClipData clipData = data.getClipData();
            uriList.clear();

            if (clipData != null) {


                for (int index = 0; index < clipData.getItemCount(); index++) {
                    ClipData.Item item = clipData.getItemAt(index);
                    Uri uri = item.getUri();
                    uriList.add(uri);

                }
                if (uriList.size() > 5) {
                    for (int i = 0; i <= 4; i++) {
                        selectedImage.add(uriList.get(i));
                    }
                    Toast.makeText(this, "size1" + selectedImage.size(), Toast.LENGTH_SHORT).show();

                    UpdateUI(selectedImage);
                } else {

                    selectedImage = uriList;
                    UpdateUI(selectedImage);
                    Toast.makeText(this, "size2" + selectedImage.size(), Toast.LENGTH_SHORT).show();

                }
            } else {

                Toast.makeText(this, "اختر مجموعة صور  حد ادنى 2 حد اقصى 5", Toast.LENGTH_LONG).show();
            }


        }
    }

    @Override
    public void OnDataSuccess(UserModel userModel) {
       // Log.e("id", userModel.getUser_id());
        this.userModel=userModel;
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("user_type"))
            {
                user_type = intent.getStringExtra("user_type");
                if (user_type.equals("visitor"))
                {
                    detail_book.setVisibility(View.INVISIBLE);
                    Upload.setVisibility(View.INVISIBLE);
                }

            }
            titlee = intent.getStringExtra("title");
            desce = intent.getStringExtra("detail");
            pricee = intent.getStringExtra("price");

            imgg = intent.getStringExtra("img");
             idoffer = intent.getStringExtra("id_offer");
        }
    }
    private void UpdateUI(List<Uri> uriList) {

        enCodeImage(uriList);
    }
    private List<String> enCodeImage(List<Uri> imagesUri) {

        List<Bitmap> bitmapList = new ArrayList<>();
        for (int i = 0; i < imagesUri.size(); i++) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imagesUri.get(i)));
                bitmapList.add(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (bitmapList.size() > 0) {
            for (int i = 0; i < bitmapList.size(); i++) {
                Bitmap bitmap = bitmapList.get(i);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);

                byte[] bytes = outputStream.toByteArray();

                enCodedImageList.add(Base64.encodeToString(bytes, Base64.DEFAULT));

                Log.e("imaaaaaaaaaaaaaaaaage", enCodedImageList.get(i));

            }
        }
        return enCodedImageList;

    }
}
