package com.Alatheer.Projects.lailaky.Activites;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Alatheer.Projects.lailaky.Adapter.ImagesAdapter;
import com.Alatheer.Projects.lailaky.ApiServices.Tags;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Classic_Shape1;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Pinboard_Shape9;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Classic_Shape2;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Classic_Shape3;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Classic_Shape4;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Classic_Shape5;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Classic_Shape6;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Classic_Shape7;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Classic_Shape8;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Classic_Shape9;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Pinboard_Shape1;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Pinboard_Shape2;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Pinboard_Shape3;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Pinboard_Shape4;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Pinboard_Shape5;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Pinboard_Shape6;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Pinboard_Shape7;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Pinboard_Shape8;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame1;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame10;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame11;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame12;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame13;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame14;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame15;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame16;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame17;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame18;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame19;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame2;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame20;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame21;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame22;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame24;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame25;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame3;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame4;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame5;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame6;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame7;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame8;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster_Frame9;
import com.Alatheer.Projects.lailaky.R;
import com.Alatheer.Projects.lailaky.SingleTone.FinalAlbumImage;
import com.Alatheer.Projects.lailaky.share.Common;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class DisplayImagesActivity extends AppCompatActivity {
    private RecyclerView recView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter adapter;
    private List<String> imageUrl;
    private  int IMG_REQ;
    private String type="";
    private int pos=0;
    private String user_id="",offer_id="",paper_id="";
    private int album_size=0;
    private CardView card_container;
    private ImageView img_close;
    private BottomSheetBehavior bottomSheetBehavior;
    private View bottom_root;
    private TextView tv_more;
    private Fragment_Classic_Shape1 classicShape1;
    private Fragment_Classic_Shape2 classicShape2;
    private Fragment_Classic_Shape3 classicShape3;
    private Fragment_Classic_Shape4 classicShape4;
    private Fragment_Classic_Shape5 classicShape5;
    private Fragment_Classic_Shape6 classicShape6;
    private Fragment_Classic_Shape7 classicShape7;
    private Fragment_Classic_Shape8 classicShape8;
    private Fragment_Classic_Shape9 classicShape9;

    private Fragment_Pinboard_Shape1 pinboardShape1;
    private Fragment_Pinboard_Shape2 pinboardShape2;
    private Fragment_Pinboard_Shape3 pinboardShape3;
    private Fragment_Pinboard_Shape4 pinboardShape4;
    private Fragment_Pinboard_Shape5 pinboardShape5;
    private Fragment_Pinboard_Shape6 pinboardShape6;
    private Fragment_Pinboard_Shape7 pinboardShape7;
    private Fragment_Pinboard_Shape8 pinboardShape8;
    private Fragment_Pinboard_Shape9 pinboardShape9;


    private Fragment_Poster_Frame1 posterframe1;
    private Fragment_Poster_Frame2 posterframe2;
    private Fragment_Poster_Frame3 posterframe3;
    private Fragment_Poster_Frame4 posterframe4;
    private Fragment_Poster_Frame5 posterframe5;
    private Fragment_Poster_Frame6 posterframe6;
    private Fragment_Poster_Frame7 posterframe7;
    private Fragment_Poster_Frame8 posterframe8;
    private Fragment_Poster_Frame9 posterframe9;
    private Fragment_Poster_Frame10 posterframe10;
    private Fragment_Poster_Frame11 posterframe11;
    private Fragment_Poster_Frame12 posterframe12;
    private Fragment_Poster_Frame13 posterframe13;
    private Fragment_Poster_Frame14 posterframe14;
    private Fragment_Poster_Frame15 posterframe15;
    private Fragment_Poster_Frame16 posterframe16;
    private Fragment_Poster_Frame17 posterframe17;
    private Fragment_Poster_Frame18 posterframe18;
    private Fragment_Poster_Frame19 posterframe19;
    private Fragment_Poster_Frame20 posterframe20;
    private Fragment_Poster_Frame21 posterframe21;
    private Fragment_Poster_Frame22 posterframe22;

    private Fragment_Poster_Frame24 posterframe24;
    private Fragment_Poster_Frame25 posterframe25;

    private List<Bitmap> bitmapList;
    private Button btn_save;
    private FinalAlbumImage instance;
    private AlertDialog dialog;
    private final String read_perm = Manifest.permission.READ_EXTERNAL_STORAGE;
    private final int read_req = 102;


    @Override
    protected void attachBaseContext(Context newBase) {


        Paper.init(newBase);


        super.attachBaseContext(LanguageHelper.onAttach(newBase, Paper.book().read("language", Locale.getDefault().getLanguage())));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_images);
        CheckPermission();
        initView();
    }

    private void CheckPermission() {
        if (ContextCompat.checkSelfPermission(this,read_perm)!= PackageManager.PERMISSION_GRANTED)
        {
            String [] per = {read_perm};
            ActivityCompat.requestPermissions(this,per, read_req);
        }
    }

    private void CreateAlertDialog(int count) {
        int c = album_size-count;
        dialog = new AlertDialog.Builder(this)
                .setMessage(getString(R.string.do_se)+" "+album_size+"\n"+getString(R.string.rem)+" "+c)
                .setCancelable(true)
                .setPositiveButton(R.string.another_page, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //instance.increaseCount();

                        if (instance.getCount()<album_size)
                        {

                            UpdateFragmentUi();
                            finish();

                        }else
                            {
                                instance.setCount(album_size);
                                btn_save.setVisibility(View.VISIBLE);
                                Toast.makeText(DisplayImagesActivity.this, R.string.img_not_eno, Toast.LENGTH_SHORT).show();
                            }
                    }
                }).setNegativeButton(R.string.view2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        NavigatetoFinalAlbum();

                    }
                })
                .create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void displayImage(int img_req) {
        IMG_REQ = img_req;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        startActivityForResult(intent.createChooser(intent,"Choose image"),IMG_REQ);


    }

    private void initView() {
        instance = FinalAlbumImage.getInstance();
        bitmapList = new ArrayList<>();
        getDataFromIntent();
        //Toast.makeText(this, "noo"+paper_id, Toast.LENGTH_SHORT).show();

        bottom_root = findViewById(R.id.bottom_root);
        bottomSheetBehavior = BottomSheetBehavior.from(bottom_root);
        card_container = findViewById(R.id.card_container);
        img_close = findViewById(R.id.img_close);
        imageUrl = new ArrayList<>();
        recView = findViewById(R.id.recView);
        manager = new GridLayoutManager(this,3);
        recView.setLayoutManager(manager);
        adapter = new ImagesAdapter(this,imageUrl);
        recView.setAdapter(adapter);
        card_container.setVisibility(View.GONE);
        tv_more = findViewById(R.id.tv_more);
        btn_save = findViewById(R.id.btn_save);
        tv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayImage(IMG_REQ);
            }
        });
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

            }
        });
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        card_container.setVisibility(View.VISIBLE);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        card_container.setVisibility(View.GONE);

                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                        card_container.setVisibility(View.VISIBLE);

                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        card_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instance.increaseCount();
                if (instance.getCount()>album_size)
                {
                    instance.setCount(album_size);
                    CreateAlertDialog(album_size);

                }
                else
                    {



                        SaveImage();
                        CreateAlertDialog(instance.getCount());

                    }

               /* instance.increaseCount();
                Log.e("count",instance.getCount()+"");
                if (instance.getCount()<=album_size)
                {



                }else
                    {
                        Toast.makeText(DisplayImagesActivity.this, "عدد صور الالبوم لاتكفي", Toast.LENGTH_SHORT).show();
                    }*/
            }
        });
    }

    private void UpdateFragmentUi() {
        btn_save.setVisibility(View.INVISIBLE);
    }

    private void SaveImage() {
        if (type.equals(Tags.Classic))
        {
            switch (this.pos)
            {
                case 0:
                    //classicShape1.getImageUri(uri);
                    bitmapList.clear();
                    bitmapList.add(classicShape1.getBitmap());
                    instance.setImages(bitmapList);
                    classicShape1.clearUi();
                    break;
                case 1:
                    bitmapList.clear();
                    bitmapList.add(classicShape2.getBitmap());
                    instance.setImages(bitmapList);

                    classicShape2.clearUi();
                    break;
                case 2:
                    bitmapList.clear();
                    bitmapList.add(classicShape3.getBitmap());
                    instance.setImages(bitmapList);

                    classicShape3.clearUi();
                    break;
                case 3:
                    bitmapList.clear();
                    bitmapList.add(classicShape4.getBitmap());
                    instance.setImages(bitmapList);

                    classicShape4.clearUi();
                    break;
                case 4:
                    bitmapList.clear();
                    bitmapList.add(classicShape5.getBitmap());
                    instance.setImages(bitmapList);

                    classicShape5.clearUi();
                    break;
                case 5:
                    bitmapList.clear();
                    bitmapList.add(classicShape6.getBitmap());
                    instance.setImages(bitmapList);

                    classicShape6.clearUi();
                    break;
                case 6:
                    bitmapList.clear();
                    bitmapList.add(classicShape7.getBitmap());
                    instance.setImages(bitmapList);

                    classicShape7.clearUi();
                    break;
                case 7:
                    bitmapList.clear();
                    bitmapList.add(classicShape8.getBitmap());
                    instance.setImages(bitmapList);

                    classicShape8.clearUi();
                    break;
                case 8:
                    bitmapList.clear();
                    bitmapList.add(classicShape9.getBitmap());
                    instance.setImages(bitmapList);

                    classicShape9.clearUi();
                    break;


            }
        }else if (type.equals(Tags.Pinboard))
        {
            switch (this.pos)
            {
                case 0:
                    bitmapList.clear();
                    bitmapList.add(pinboardShape1.getBitmap());
                    instance.setImages(bitmapList);
                    pinboardShape1.clearUi();
                    break;
                case 1:
                    bitmapList.clear();
                    bitmapList.add(pinboardShape2.getBitmap());
                    instance.setImages(bitmapList);
                    pinboardShape2.clearUi();
                    break;
                case 2:
                    bitmapList.clear();
                    bitmapList.add(pinboardShape3.getBitmap());
                    instance.setImages(bitmapList);
                    pinboardShape3.clearUi();
                    break;
                case 3:
                    bitmapList.clear();
                    bitmapList.add(pinboardShape4.getBitmap());
                    instance.setImages(bitmapList);
                    pinboardShape4.clearUi();
                    break;
                case 4:
                    bitmapList.clear();
                    bitmapList.add(pinboardShape5.getBitmap());
                    instance.setImages(bitmapList);
                    pinboardShape5.clearUi();
                    break;
                case 5:
                    bitmapList.clear();
                    bitmapList.add(pinboardShape6.getBitmap());
                    instance.setImages(bitmapList);
                    pinboardShape6.clearUi();
                    break;
                case 6:
                    bitmapList.clear();
                    bitmapList.add(pinboardShape7.getBitmap());
                    instance.setImages(bitmapList);
                    pinboardShape7.clearUi();
                    break;
                case 7:
                    bitmapList.clear();
                    bitmapList.add(pinboardShape8.getBitmap());
                    instance.setImages(bitmapList);
                    pinboardShape8.clearUi();
                    break;
                case 8:
                    bitmapList.clear();
                    bitmapList.add(pinboardShape9.getBitmap());
                    instance.setImages(bitmapList);
                    pinboardShape9.clearUi();
                    break;




            }
        }
        else if (type.equals(Tags.Poster))
        {

            switch (this.pos)
            {
                case 0:
                    bitmapList.clear();
                    bitmapList.add(posterframe1.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe1.clearUi();
                    break;
                case 1:
                    bitmapList.clear();
                    bitmapList.add(posterframe2.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe2.clearUi();
                    break;

                case 2:
                    bitmapList.clear();
                    bitmapList.add(posterframe3.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe3.clearUi();
                    break;
                case 3:
                    bitmapList.clear();
                    bitmapList.add(posterframe4.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe4.clearUi();
                    break;
                case 4:
                    bitmapList.clear();
                    bitmapList.add(posterframe5.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe5.clearUi();
                    break;
                case 5:
                    bitmapList.clear();
                    bitmapList.add(posterframe6.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe6.clearUi();
                    break;
                case 6:
                    bitmapList.clear();
                    bitmapList.add(posterframe7.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe7.clearUi();
                    break;
                case 7:
                    bitmapList.clear();
                    bitmapList.add(posterframe8.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe8.clearUi();
                    break;
                case 8:
                    bitmapList.clear();
                    bitmapList.add(posterframe9.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe9.clearUi();
                    break;


                case 9:
                    bitmapList.clear();
                    bitmapList.add(posterframe10.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe10.clearUi();
                    break;

                case 10:
                    bitmapList.clear();
                    bitmapList.add(posterframe11.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe11.clearUi();
                    break;
                case 11:
                    bitmapList.clear();
                    bitmapList.add(posterframe12.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe12.clearUi();
                    break;
                case 12:
                    bitmapList.clear();
                    bitmapList.add(posterframe13.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe13.clearUi();
                    break;
                case 13:
                    bitmapList.clear();
                    bitmapList.add(posterframe14.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe14.clearUi();
                    break;
                case 14:
                    bitmapList.clear();
                    bitmapList.add(posterframe15.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe15.clearUi();
                    break;
                case 15:
                    bitmapList.clear();
                    bitmapList.add(posterframe16.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe16.clearUi();
                    break;
                case 16:
                    bitmapList.clear();
                    bitmapList.add(posterframe17.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe17.clearUi();
                    break;
                case 17:
                    bitmapList.clear();
                    bitmapList.add(posterframe18.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe18.clearUi();
                    break;

                case 18:
                    bitmapList.clear();
                    bitmapList.add(posterframe19.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe19.clearUi();
                    break;

                case 19:
                    bitmapList.clear();
                    bitmapList.add(posterframe20.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe20.clearUi();
                    break;
                case 20:
                    bitmapList.clear();
                    bitmapList.add(posterframe21.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe21.clearUi();
                    break;

                case 21:
                    bitmapList.clear();
                    bitmapList.add(posterframe22.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe22.clearUi();
                    break;


                case 22:
                    bitmapList.clear();
                    bitmapList.add(posterframe24.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe24.clearUi();
                    break;

                case 23:
                    bitmapList.clear();
                    bitmapList.add(posterframe25.getBitmap());
                    instance.setImages(bitmapList);
                    posterframe25.clearUi();
                    break;

            }

        }

    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent!=null)
        {
            type = intent.getStringExtra("type");
            pos = intent.getIntExtra("position",0);
            user_id = intent.getStringExtra("user_id");
            offer_id =intent.getStringExtra("id_offer");
            paper_id =intent.getStringExtra("paper_id");
            album_size = intent.getIntExtra("album_size",0);
            Log.e("fclassic",album_size+"");
            Log.e("fclassic",user_id+"");
            Log.e("fclassic",offer_id+"");

            UpdateUi(type,pos);
        }
    }

    private void UpdateUi(String type, int pos) {
       /* Log.e("type",type);
        Log.e("pos",pos+"");*/
        if (type.equals(Tags.Classic))
        {
            switch (pos)
            {
                case 0:
                    classicShape1 = Fragment_Classic_Shape1.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,classicShape1).commit();
                    break;
                case 1:
                    classicShape2 = Fragment_Classic_Shape2.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape2).commit();

                    break;
                case 2:
                    classicShape3 = Fragment_Classic_Shape3.getInstance(user_id,offer_id,paper_id,album_size);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape3).commit();

                    break;
                case 3:
                    classicShape4 = Fragment_Classic_Shape4.getInstance(user_id,offer_id,paper_id,album_size);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape4).commit();

                    break;
                case 4:
                    classicShape5 = Fragment_Classic_Shape5.getInstance(user_id,offer_id,paper_id,album_size);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape5).commit();

                    break;
                case 5:
                    classicShape6 = Fragment_Classic_Shape6.getInstance(user_id,offer_id,paper_id,album_size);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape6).commit();

                    break;
                case 6:
                    classicShape7 = Fragment_Classic_Shape7.getInstance(user_id,offer_id,paper_id,album_size);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape7).commit();

                    break;
                case 7:
                    classicShape8 = Fragment_Classic_Shape8.getInstance(user_id,offer_id,paper_id,album_size);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape8).commit();

                    break;
                case 8:
                    classicShape9 = Fragment_Classic_Shape9.getInstance(user_id,offer_id,paper_id,album_size);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape9).commit();

                    break;


            }
        }else if (type.equals(Tags.Pinboard))
        {
            switch (pos)
            {
                case 0:
                    pinboardShape1 = Fragment_Pinboard_Shape1.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape1).commit();
                    break;
                case 1:
                    pinboardShape2 = Fragment_Pinboard_Shape2.getInstance(user_id,offer_id,paper_id,album_size);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape2).commit();

                    break;
                case 2:
                    pinboardShape3 = Fragment_Pinboard_Shape3.getInstance(user_id,offer_id,paper_id,album_size);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape3).commit();

                    break;
                case 3:
                    pinboardShape4 = Fragment_Pinboard_Shape4.getInstance(user_id,offer_id,paper_id,album_size);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape4).commit();

                    break;
                case 4:
                    pinboardShape5 = Fragment_Pinboard_Shape5.getInstance(user_id,offer_id,paper_id,album_size);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape5).commit();

                    break;
                case 5:
                    pinboardShape6 = Fragment_Pinboard_Shape6.getInstance(user_id,offer_id,paper_id,album_size);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape6).commit();

                    break;
                case 6:
                    pinboardShape7 = Fragment_Pinboard_Shape7.getInstance(user_id,offer_id,paper_id,album_size);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape7).commit();

                    break;
                case 7:
                    pinboardShape8 = Fragment_Pinboard_Shape8.getInstance(user_id,offer_id,paper_id,album_size);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape8).commit();

                    break;
                case 8:
                    pinboardShape9 = Fragment_Pinboard_Shape9.getInstance(user_id,offer_id,paper_id,album_size);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape9).commit();

                    break;




            }
        }else if (type.equals(Tags.Poster))
        {

            switch (pos) {
                case 0:
                    posterframe1 = Fragment_Poster_Frame1.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe1).commit();
                    break;
                case 1:
                    posterframe2 = Fragment_Poster_Frame2.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe2).commit();
                    break;
                case 2:
                    posterframe3 = Fragment_Poster_Frame3.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe3).commit();
                    break;
                case 3:
                    posterframe4 = Fragment_Poster_Frame4.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe4).commit();
                    break;
                case 4:
                    posterframe5 = Fragment_Poster_Frame5.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe5).commit();
                    break;
                case 5:
                    posterframe6 = Fragment_Poster_Frame6.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe6).commit();
                    break;
                case 6:
                    posterframe7 = Fragment_Poster_Frame7.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe7).commit();
                    break;
                case 7:
                    posterframe8 = Fragment_Poster_Frame8.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe8).commit();
                    break;
                case 8:
                    posterframe9 = Fragment_Poster_Frame9.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe9).commit();
                    break;

                case 9:
                    posterframe10 = Fragment_Poster_Frame10.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe10).commit();
                    break;
                case 10:
                    posterframe11 = Fragment_Poster_Frame11.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe11).commit();
                    break;
                case 11:
                    posterframe12 = Fragment_Poster_Frame12.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe12).commit();
                    break;
                case 12:
                    posterframe13 = Fragment_Poster_Frame13.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe13).commit();
                    break;
                case 13:
                    posterframe14 = Fragment_Poster_Frame14.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe14).commit();
                    break;
                case 14:
                    posterframe15 = Fragment_Poster_Frame15.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe15).commit();
                    break;
                case 15:
                    posterframe16 = Fragment_Poster_Frame16.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe16).commit();
                    break;
                case 16:
                    posterframe17 = Fragment_Poster_Frame17.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe17).commit();
                    break;
                case 17:
                    posterframe18 = Fragment_Poster_Frame18.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe18).commit();
                    break;
                case 18:
                    posterframe19 = Fragment_Poster_Frame19.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe19).commit();
                    break;
                case 19:
                    posterframe20 = Fragment_Poster_Frame20.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe20).commit();
                    break;
                case 20:
                    posterframe21 = Fragment_Poster_Frame21.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe21).commit();
                    break;
                case 21:
                    posterframe22 = Fragment_Poster_Frame22.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe22).commit();
                    break;

                case 22:
                    posterframe24 = Fragment_Poster_Frame24.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe24).commit();
                    break;
                case 23:
                    posterframe25 = Fragment_Poster_Frame25.getInstance(user_id,offer_id,paper_id,album_size);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe25).commit();
                    break;

            }

        }else if (type.equals(Tags.custom))
        {
           Intent intent = new Intent(DisplayImagesActivity.this,CustomShapeActivity.class);
           intent.putExtra("album_size",album_size);
           intent.putExtra("user_id",user_id);
           intent.putExtra("id_offer",offer_id);
            intent.putExtra("paper_id",paper_id);
            startActivity(intent);
           finish();

    }
    }

    public void SetPos(int pos)
    {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        String uri = imageUrl.get(pos);

        if (type.equals(Tags.Classic))
        {
            switch (this.pos)
            {
                case 0:
                    classicShape1.getImageUri(uri);
                    break;
                case 1:
                    classicShape2.getImageUri(uri);

                    break;
                case 2:
                    classicShape3.getImageUri(uri);

                    break;
                case 3:
                    classicShape4.getImageUri(uri);

                    break;
                case 4:
                    classicShape5.getImageUri(uri);

                    break;
                case 5:
                    classicShape6.getImageUri(uri);

                    break;
                case 6:
                    classicShape7.getImageUri(uri);

                    break;
                case 7:
                    classicShape8.getImageUri(uri);

                    break;
                case 8:
                    classicShape9.getImageUri(uri);

                    break;


            }
        }else if (type.equals(Tags.Pinboard))
        {
            switch (this.pos)
            {
                case 0:
                    pinboardShape1.getImageUri(uri);
                    break;
                case 1:
                    pinboardShape2.getImageUri(uri);

                    break;
                case 2:
                    pinboardShape3.getImageUri(uri);

                    break;
                case 3:
                    pinboardShape4.getImageUri(uri);

                    break;
                case 4:
                    pinboardShape5.getImageUri(uri);

                    break;
                case 5:
                    pinboardShape6.getImageUri(uri);

                    break;
                case 6:
                    pinboardShape7.getImageUri(uri);

                    break;
                case 7:
                    pinboardShape8.getImageUri(uri);

                    break;
                case 8:
                    pinboardShape9.getImageUri(uri);

                    break;




            }
        }
        else if (type.equals(Tags.Poster))
        {

            switch (this.pos)
            {
                case 0:
                    posterframe1.getImageUri(uri);
                    break;
                case 1:
                    posterframe2.getImageUri(uri);
                    break;

                case 2:
                    posterframe3.getImageUri(uri);

                    break;
                case 3:
                    posterframe4.getImageUri(uri);

                    break;
                case 4:
                    posterframe5.getImageUri(uri);

                    break;
                case 5:
                    posterframe6.getImageUri(uri);

                    break;
                case 6:
                    posterframe7.getImageUri(uri);

                    break;
                case 7:
                    posterframe8.getImageUri(uri);

                    break;
                case 8:
                    posterframe9.getImageUri(uri);

                    break;


                case 9:
                    posterframe10.getImageUri(uri);
                    break;

                case 10:
                    posterframe11.getImageUri(uri);

                    break;
                case 11:
                    posterframe12.getImageUri(uri);

                    break;
                case 12:
                    posterframe13.getImageUri(uri);

                    break;
                case 13:
                    posterframe14.getImageUri(uri);

                    break;
                case 14:
                    posterframe15.getImageUri(uri);

                    break;
                case 15:
                    posterframe16.getImageUri(uri);

                    break;
                case 16:
                    posterframe17.getImageUri(uri);

                    break;
                case 17:
                    posterframe18.getImageUri(uri);

                    break;

                case 18:
                    posterframe19.getImageUri(uri);

                    break;

                case 19:
                    posterframe20.getImageUri(uri);

                    break;
                case 20:
                    posterframe21.getImageUri(uri);

                    break;

                case 21:
                    posterframe22.getImageUri(uri);

                    break;


                case 22:
                    posterframe24.getImageUri(uri);

                    break;

                case 23:
                    posterframe25.getImageUri(uri);

                    break;

            }

        }


    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==IMG_REQ && resultCode==RESULT_OK && data!=null)
        {
            ClipData clipData = data.getClipData();
            if (clipData!=null)
            {
                if (clipData.getItemCount()<5&&imageUrl.size()==0)
                {
//                    Toast.makeText(this, "اختر على الاقل 5 صور", Toast.LENGTH_SHORT).show();

                    android.support.v7.app.AlertDialog alertDialog = Common.chooseAlertDialog(DisplayImagesActivity.this);
                    alertDialog.show();
                }else
                    {
                        for (int i =0;i<clipData.getItemCount();i++)
                        {
                            ClipData.Item item = clipData.getItemAt(i);
                            Uri uri = item.getUri();
                            String path = Common.getImagePath(DisplayImagesActivity.this,uri);


                            imageUrl.add(path);

                        }
                        adapter.notifyDataSetChanged();

                        card_container.setVisibility(View.VISIBLE);
                    }


            }else
                {
                    /*Uri uri = data.getData();
                    imageUrl.add(uri.toString());
                    adapter.notifyDataSetChanged();
                    card_container.setVisibility(View.VISIBLE);*/

                    if (imageUrl.size()>=5)
                    {
                        Uri uri = data.getData();
                        String path = Common.getImagePath(DisplayImagesActivity.this,uri);

                        imageUrl.add(path);
                        adapter.notifyDataSetChanged();
                    }else
                        {
//                            Toast.makeText(this, R.string.sel_min, Toast.LENGTH_SHORT).show();

                            android.support.v7.app.AlertDialog alertDialog = Common.chooseAlertDialog(DisplayImagesActivity.this);
                            alertDialog.show();

                        }
                }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==read_req)
        {
            if (grantResults.length>0)
            {
                if (grantResults[0]!=PackageManager.PERMISSION_GRANTED)
                {
                    finish();
                }
            }
        }
    }

    public void setButtonsaveVisibility(int visibility)
    {
        switch (visibility)
        {
            case Tags.visible_btn:
                btn_save.setVisibility(View.VISIBLE);
                break;

            case Tags.invisible_btn:
                btn_save.setVisibility(View.GONE);

                break;
        }
    }

    public void NavigatetoFinalAlbum()
    {
        Log.e("sdfsdfsd","dddddddd");
        Intent intent = new Intent(DisplayImagesActivity.this,FinalAlbumActivity.class);
        intent.putExtra("user_id",user_id);
        intent.putExtra("id_offer",offer_id);
        intent.putExtra("paper_id",paper_id);

        startActivity(intent);
        finish();
    }

}
