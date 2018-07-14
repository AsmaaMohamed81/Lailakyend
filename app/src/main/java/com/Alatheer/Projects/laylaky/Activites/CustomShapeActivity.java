package com.Alatheer.Projects.laylaky.Activites;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.Alatheer.Projects.laylaky.R;
import com.jcmore2.collage.CollageView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class CustomShapeActivity extends AppCompatActivity {
    private final int IMG_REQ = 336;
    private List<Bitmap> bitmapList;
    private CollageView collageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_shape);
        initView();

    }

    private void initView() {
        bitmapList = new ArrayList<>();
        collageView = findViewById(R.id.collageView);
        collageView.setFixedCollage(false);
        displayImage();

    }
    public void displayImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        startActivityForResult(intent.createChooser(intent,"Choose image"),IMG_REQ);


    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==IMG_REQ && resultCode==RESULT_OK && data!=null)
        {
            ClipData clipData = data.getClipData();
            if (clipData!=null)
            {
                if (clipData.getItemCount()<5&&bitmapList.size()==0)
                {
                    Toast.makeText(this, "اختر على الاقل 5 صور", Toast.LENGTH_SHORT).show();
                     new Thread(new Runnable() {
                        @Override
                        public void run() {
                            displayImage();

                        }
                    }).start();


                }else
                {
                    for (int i =0;i<clipData.getItemCount();i++)
                    {
                        ClipData.Item item = clipData.getItemAt(i);
                        try {
                            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(item.getUri()));
                            bitmapList.add(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                    collageView.createCollageBitmaps(bitmapList);
                    collageView.requestLayout();
                    collageView.invalidate();
                    //card_container.setVisibility(View.VISIBLE);
                }


            }else
            {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        displayImage();

                    }
                }).start();
                Toast.makeText(this, "اختر على الاقل 5 صور", Toast.LENGTH_SHORT).show();


            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
