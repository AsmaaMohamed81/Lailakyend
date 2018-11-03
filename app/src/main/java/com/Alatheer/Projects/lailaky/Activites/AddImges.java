package com.Alatheer.Projects.lailaky.Activites;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.Alatheer.Projects.lailaky.R;

public class AddImges extends AppCompatActivity {
ImageView   img1 ,img2 ,img3 ,img4 ,img5 ,img6;
    byte[] photo;
    private Bitmap bp1,bp2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_imges);
        img1=findViewById(R.id.btnSelectImage1);
        img2=findViewById(R.id.btnSelectImage2);
        img3=findViewById(R.id.btnSelectImage3);
        img4=findViewById(R.id.btnSelectImage4);
        img5=findViewById(R.id.btnSelectImage5);
        img6=findViewById(R.id.btnSelectImage6);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK
                        ,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 2);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent photoPickerIntent1 = new Intent(Intent.ACTION_PICK
                        ,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                photoPickerIntent1.setType("image/*");
                startActivityForResult(photoPickerIntent1, 3);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 2:
                if(resultCode == RESULT_OK){
                    Uri choosenImage = data.getData();

                    if(choosenImage !=null){

                        bp1=decodeUri(choosenImage, 100);

                        img1.setImageBitmap(bp1);

                    }
                    break;
                }

            case 3:
                if(resultCode == RESULT_OK){
                    Uri choosenImage = data.getData();

                    if(choosenImage !=null){

                        bp2=decodeUri(choosenImage, 100);

                        img2.setImageBitmap(bp2);

                    }
                    break;
                }
        }
    }


    protected Bitmap decodeUri(Uri selectedImage, int REQUIRED_SIZE) {

        try {

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

            // The new size we want to scale to
            // final int REQUIRED_SIZE =  size;

            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE
                        || height_tmp / 2 < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
