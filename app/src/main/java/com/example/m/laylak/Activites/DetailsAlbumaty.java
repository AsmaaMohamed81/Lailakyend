package com.example.m.laylak.Activites;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.m.laylak.Adapter.CustomGalleryAdapter;
import com.example.m.laylak.R;

import java.net.URI;
import java.util.List;

public class DetailsAlbumaty extends AppCompatActivity {
    Gallery simpleGallery;
    CustomGalleryAdapter customGalleryAdapter;
    ImageView selectedImageView;
    // array of images
   /* int[] images = {R.drawable.images1, R.drawable.images2, R.drawable.album1, R.drawable.album2, R.drawable.album3,
            R.drawable.images1, R.drawable.images2, R.drawable.album1, R.drawable.album2, R.drawable.album3, R.drawable.images1,
            R.drawable.images2, R.drawable.album3};*/
   List<Uri> uriList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_albumaty);

        simpleGallery = (Gallery) findViewById(R.id.simpleGallery); // get the reference of Gallery
        selectedImageView = (ImageView) findViewById(R.id.selectedImageView); // get the reference of ImageView

        /*customGalleryAdapter = new CustomGalleryAdapter(getApplicationContext(), images); // initialize the adapter
        simpleGallery.setAdapter(customGalleryAdapter);*/ // set the adapter


        // perform setOnItemClickListener event on the Gallery
        simpleGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set the selected image in the ImageView
                selectedImageView.setImageURI(uriList.get(position));
            }
        });

        getDataFromIntent();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent!=null)
        {
            uriList = (List<Uri>) intent.getSerializableExtra("details");
            UpdateUI(uriList);
        }
    }

    private void UpdateUI(List<Uri> uriList) {

        customGalleryAdapter = new CustomGalleryAdapter(this,uriList); // initialize the adapter
        simpleGallery.setAdapter(customGalleryAdapter);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
