package com.Alatheer.Projects.lailaky.Activites;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.Alatheer.Projects.lailaky.Adapter.ImagesAdapter;
import com.Alatheer.Projects.lailaky.ApiServices.Tags;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Classic_Shape1;
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
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Pinboard_Shape9;
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
import com.Alatheer.Projects.lailaky.Fragments.Fragment_twopages_shape1;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_twopages_shape2;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_twopages_shape3;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_twopages_shape4;
import com.Alatheer.Projects.lailaky.Models.FinalImageModel;
import com.Alatheer.Projects.lailaky.R;
import com.Alatheer.Projects.lailaky.SingleTone.FinalAlbumImage;
import com.Alatheer.Projects.lailaky.share.Common;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class UpdateImageActivity extends AppCompatActivity {

    private RecyclerView recView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter adapter;
    private List<String> imageUrl;
    private int IMG_REQ;
    private String type = "";
    private int pos = 0;
    private String user_id = "", offer_id = "", paper_id = "";
    private int album_size = 0;
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


    private Fragment_twopages_shape1 fragmentTwopagesShape1;
    private Fragment_twopages_shape2 fragmentTwopagesShape2;
    private Fragment_twopages_shape3 fragmentTwopagesShape3;
    private Fragment_twopages_shape4 fragmentTwopagesShape4;


    private List<Bitmap> bitmapList;
    private Button btn_save;
    private FinalAlbumImage instance;
    private final String read_perm = Manifest.permission.READ_EXTERNAL_STORAGE;
    private final int read_req = 102;

    private  String  page_type="";

    private FinalImageModel finalImageModel_want_to_update;
    private String which_image="";


    @Override
    protected void attachBaseContext(Context newBase) {


        Paper.init(newBase);


        super.attachBaseContext(LanguageHelper.onAttach(newBase, Paper.book().read("language", Locale.getDefault().getLanguage())));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_image);
        CheckPermission();
        initView();
    }

    private void initView() {
        instance = FinalAlbumImage.getInstance();
        bitmapList = new ArrayList<>();
        getDataFromIntent();
        bottom_root = findViewById(R.id.bottom_root);
        bottomSheetBehavior = BottomSheetBehavior.from(bottom_root);
        card_container = findViewById(R.id.card_container);
        img_close = findViewById(R.id.img_close);
        imageUrl = new ArrayList<>();
        recView = findViewById(R.id.recView);
        manager = new GridLayoutManager(this, 3);
        recView.setLayoutManager(manager);
        adapter = new ImagesAdapter(this, imageUrl,"2");
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
                SaveImage();
            }
        });
    }

    private void CheckPermission()
    {
        if (ContextCompat.checkSelfPermission(this, read_perm) != PackageManager.PERMISSION_GRANTED) {
            String[] per = {read_perm};
            ActivityCompat.requestPermissions(this, per, read_req);
        }
    }
    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            type = intent.getStringExtra("frame");
            pos = intent.getIntExtra("position", 0);
            which_image = intent.getStringExtra("which_image");
            finalImageModel_want_to_update = instance.getFinalImageModel_before_update();
            Log.e("image1",finalImageModel_want_to_update.getImage1()+"_");
            Log.e("image2",finalImageModel_want_to_update.getImage2()+"_");

             UpdateUi(type, pos);
        }
    }

    private void UpdateUi(String type, int pos)
    {

        if (type.equals(Tags.Classic)) {
            page_type = Tags.type_one_page;

            switch (pos) {
                case 0:
                    classicShape1 = Fragment_Classic_Shape1.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape1).commit();
                    break;
                case 1:
                    classicShape2 = Fragment_Classic_Shape2.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape2).commit();

                    break;
                case 2:
                    classicShape3 = Fragment_Classic_Shape3.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape3).commit();

                    break;
                case 3:
                    classicShape4 = Fragment_Classic_Shape4.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape4).commit();

                    break;
                case 4:
                    classicShape5 = Fragment_Classic_Shape5.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape5).commit();

                    break;
                case 5:
                    classicShape6 = Fragment_Classic_Shape6.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape6).commit();

                    break;
                case 6:
                    classicShape7 = Fragment_Classic_Shape7.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape7).commit();

                    break;
                case 7:
                    classicShape8 = Fragment_Classic_Shape8.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape8).commit();

                    break;
                case 8:
                    classicShape9 = Fragment_Classic_Shape9.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape9).commit();

                    break;


            }
        } else if (type.equals(Tags.Pinboard)) {
            page_type = Tags.type_one_page;

            switch (pos) {
                case 0:
                    pinboardShape1 = Fragment_Pinboard_Shape1.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape1).commit();
                    break;
                case 1:
                    pinboardShape2 = Fragment_Pinboard_Shape2.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape2).commit();

                    break;
                case 2:
                    pinboardShape3 = Fragment_Pinboard_Shape3.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape3).commit();

                    break;
                case 3:
                    pinboardShape4 = Fragment_Pinboard_Shape4.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape4).commit();

                    break;
                case 4:
                    pinboardShape5 = Fragment_Pinboard_Shape5.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape5).commit();

                    break;
                case 5:
                    pinboardShape6 = Fragment_Pinboard_Shape6.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape6).commit();

                    break;
                case 6:
                    pinboardShape7 = Fragment_Pinboard_Shape7.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape7).commit();

                    break;
                case 7:
                    pinboardShape8 = Fragment_Pinboard_Shape8.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape8).commit();

                    break;
                case 8:
                    pinboardShape9 = Fragment_Pinboard_Shape9.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape9).commit();

                    break;


            }
        } else if (type.equals(Tags.Poster)) {
            page_type = Tags.type_one_page;

            switch (pos) {
                case 0:
                    posterframe1 = Fragment_Poster_Frame1.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe1).commit();
                    break;
                case 1:
                    posterframe2 = Fragment_Poster_Frame2.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe2).commit();
                    break;
                case 2:
                    posterframe3 = Fragment_Poster_Frame3.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe3).commit();
                    break;
                case 3:
                    posterframe4 = Fragment_Poster_Frame4.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe4).commit();
                    break;
                case 4:
                    posterframe5 = Fragment_Poster_Frame5.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe5).commit();
                    break;
                case 5:
                    posterframe6 = Fragment_Poster_Frame6.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe6).commit();
                    break;
                case 6:
                    posterframe7 = Fragment_Poster_Frame7.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe7).commit();
                    break;
                case 7:
                    posterframe8 = Fragment_Poster_Frame8.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe8).commit();
                    break;
                case 8:
                    posterframe9 = Fragment_Poster_Frame9.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe9).commit();
                    break;

                case 9:
                    posterframe10 = Fragment_Poster_Frame10.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe10).commit();
                    break;
                case 10:
                    posterframe11 = Fragment_Poster_Frame11.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe11).commit();
                    break;
                case 11:
                    posterframe12 = Fragment_Poster_Frame12.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe12).commit();
                    break;
                case 12:
                    posterframe13 = Fragment_Poster_Frame13.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe13).commit();
                    break;
                case 13:
                    posterframe14 = Fragment_Poster_Frame14.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe14).commit();
                    break;
                case 14:
                    posterframe15 = Fragment_Poster_Frame15.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe15).commit();
                    break;
                case 15:
                    posterframe16 = Fragment_Poster_Frame16.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe16).commit();
                    break;
                case 16:
                    posterframe17 = Fragment_Poster_Frame17.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe17).commit();
                    break;
                case 17:
                    posterframe18 = Fragment_Poster_Frame18.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe18).commit();
                    break;
                case 18:
                    posterframe19 = Fragment_Poster_Frame19.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe19).commit();
                    break;
                case 19:
                    posterframe20 = Fragment_Poster_Frame20.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe20).commit();
                    break;
                case 20:
                    posterframe21 = Fragment_Poster_Frame21.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe21).commit();
                    break;
                case 21:
                    posterframe22 = Fragment_Poster_Frame22.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe22).commit();
                    break;

                case 22:
                    posterframe24 = Fragment_Poster_Frame24.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe24).commit();
                    break;
                case 23:
                    posterframe25 = Fragment_Poster_Frame25.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, posterframe25).commit();
                    break;

            }

        } else if (type.equals(Tags.custom)) {
            Intent intent = new Intent(UpdateImageActivity.this, CustomShapeActivity.class);
            intent.putExtra("album_size", album_size);
            intent.putExtra("user_id", user_id);
            intent.putExtra("id_offer", offer_id);
            intent.putExtra("paper_id", paper_id);
            startActivity(intent);
            finish();

        } else if (type.equals(Tags.twopager)) {
            page_type = Tags.type_two_pages;

            switch (pos) {
                case 0:
                    fragmentTwopagesShape1 = Fragment_twopages_shape1.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentTwopagesShape1).commit();
                    break;
                case 1:
                    fragmentTwopagesShape2 = Fragment_twopages_shape2.getInstance(user_id, offer_id, paper_id,album_size,Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentTwopagesShape2).commit();
                    break;
                case 3:
                    fragmentTwopagesShape3 = Fragment_twopages_shape3.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentTwopagesShape3).commit();
                    break;
                case 4:
                    fragmentTwopagesShape4 = Fragment_twopages_shape4.getInstance(user_id, offer_id, paper_id, album_size, Tags.UpdateImageActivity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentTwopagesShape4).commit();
                    break;


            }

        }
    }
    public void displayImage(int img_req) {
        IMG_REQ = img_req;

        Intent intent;
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT)
        {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        }else
        {
            intent = new Intent(Intent.ACTION_GET_CONTENT);

        }
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);

        startActivityForResult(intent.createChooser(intent, "Choose image"), IMG_REQ);


    }

    private void UpdateFragmentUi() {
        btn_save.setVisibility(View.INVISIBLE);
    }

    private void SaveImage() {
        if (type.equals(Tags.Classic)) {

            switch (this.pos) {

                case 0:

                    int pos_for_update;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(classicShape1.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Classic);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                        {
                            byte [] image2 = finalImageModel_want_to_update.getImage2();
                            pos_for_update = instance.getItemPositionInImages(image2);
                            byte [] newImage2 = getByteArrayFromBitmap(classicShape1.getBitmap());

                            finalImageModel_want_to_update.setImage2(newImage2);

                            FinalImageModel finalImageModel = new FinalImageModel();
                            finalImageModel.setType(Tags.type_one_page);
                            finalImageModel.setImage1(newImage2);
                            finalImageModel.setFrame_type(Tags.Classic);
                            finalImageModel.setPosition_on_frame(this.pos);
                            instance.updateItemInImageList(finalImageModel,pos_for_update);
                            ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                        }




                    classicShape1.clearUi();
                    break;
                case 1:

                    int pos_for_update2;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update2 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(classicShape2.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Classic);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update2);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update2 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(classicShape2.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Classic);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update2);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }
                    classicShape2.clearUi();
                    break;
                case 2:
                    int pos_for_update3;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update3 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(classicShape3.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Classic);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update3);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update3 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(classicShape3.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Classic);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update3);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }
                    classicShape3.clearUi();
                    break;
                case 3:
                    int pos_for_update4;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update4 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(classicShape4.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Classic);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update4);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update4 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(classicShape4.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Classic);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update4);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    classicShape4.clearUi();
                    break;
                case 4:
                    int pos_for_update5;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update5 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(classicShape5.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Classic);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update5);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update5 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(classicShape5.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Classic);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update5);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }
                    classicShape5.clearUi();
                    break;
                case 5:
                    int pos_for_update6;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update6 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(classicShape6.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Classic);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update6);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update6 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(classicShape6.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Classic);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update6);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    classicShape6.clearUi();
                    break;
                case 6:
                    int pos_for_update7;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update7 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(classicShape7.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Classic);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update7);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update7 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(classicShape7.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Classic);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update7);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    classicShape7.clearUi();
                    break;
                case 7:
                    int pos_for_update8;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update8 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(classicShape8.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Classic);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update8);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update8 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(classicShape8.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Classic);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update8);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    classicShape8.clearUi();
                    break;
                case 8:
                    int pos_for_update9;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update9 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(classicShape9.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Classic);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update9);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update9 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(classicShape9.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Classic);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update9);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    classicShape9.clearUi();
                    break;


            }
        } else if (type.equals(Tags.Pinboard)) {

            switch (this.pos) {
                case 0:
                    int pos_for_update1;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update1 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(pinboardShape1.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Pinboard);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update1);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update1 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(pinboardShape1.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Pinboard);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update1);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    pinboardShape1.clearUi();
                    break;
                case 1:
                    int pos_for_update2;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update2 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(pinboardShape2.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Pinboard);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update2);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update2 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(pinboardShape2.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Pinboard);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update2);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    pinboardShape2.clearUi();
                    break;
                case 2:
                    int pos_for_update3;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update3 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(pinboardShape3.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Pinboard);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update3);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update3 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(pinboardShape3.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Pinboard);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update3);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    pinboardShape3.clearUi();
                    break;
                case 3:

                    int pos_for_update4;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update4 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(pinboardShape4.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Pinboard);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update4);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update4 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(pinboardShape4.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Pinboard);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update4);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }


                    pinboardShape4.clearUi();
                    break;
                case 4:
                    int pos_for_update5;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update5 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(pinboardShape5.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Pinboard);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update5);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update5 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(pinboardShape5.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Pinboard);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update5);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    pinboardShape5.clearUi();
                    break;
                case 5:
                    int pos_for_update6;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update6 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(pinboardShape6.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Pinboard);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update6);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update6 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(pinboardShape6.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Pinboard);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update6);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    pinboardShape6.clearUi();
                    break;
                case 6:
                    int pos_for_update7;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update7 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(pinboardShape7.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Pinboard);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update7);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update7 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(pinboardShape7.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Pinboard);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update7);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    pinboardShape7.clearUi();
                    break;
                case 7:
                    int pos_for_update8;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update8 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(pinboardShape8.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Pinboard);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update8);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update8 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(pinboardShape8.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Pinboard);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update8);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }


                    pinboardShape8.clearUi();
                    break;
                case 8:
                    int pos_for_update9;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update9 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(pinboardShape9.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Pinboard);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update9);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update9 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(pinboardShape9.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Pinboard);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update9);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }


                    pinboardShape9.clearUi();
                    break;


            }
        } else if (type.equals(Tags.Poster)) {


            switch (this.pos) {
                case 0:

                    int pos_for_update1;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update1 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe1.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update1);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update1 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe1.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update1);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    posterframe1.clearUi();
                    break;
                case 1:
                    int pos_for_update2;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update2 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe2.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update2);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update2 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe2.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update2);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    posterframe2.clearUi();
                    break;

                case 2:
                    int pos_for_update3;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update3 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe3.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update3);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update3 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe3.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update3);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    posterframe3.clearUi();
                    break;
                case 3:
                    int pos_for_update4;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update4 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe4.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update4);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update4 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe4.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update4);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    posterframe4.clearUi();
                    break;
                case 4:
                    int pos_for_update5;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update5 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe5.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update5);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update5 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe5.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update5);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    posterframe5.clearUi();
                    break;
                case 5:
                    int pos_for_update6;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update6 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe6.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update6);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update6 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe6.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update6);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    posterframe6.clearUi();

                    break;
                case 6:
                    int pos_for_update7;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update7 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe7.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update7);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update7 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe7.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update7);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    posterframe7.clearUi();
                    break;
                case 7:
                    int pos_for_update8;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update8 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe8.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update8);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update8 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe8.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update8);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }


                    posterframe8.clearUi();
                    break;
                case 8:
                    int pos_for_update9;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update9 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe9.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update9);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update9 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe9.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update9);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    posterframe9.clearUi();
                    break;


                case 9:
                    int pos_for_update10;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update10 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe10.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update10);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update10 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe10.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update10);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    posterframe10.clearUi();
                    break;

                case 10:
                    int pos_for_update11;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update11 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe11.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update11);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update11 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe11.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update11);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    posterframe11.clearUi();
                    break;
                case 11:
                    int pos_for_update12;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update12 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe12.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update12);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update12 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe12.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update12);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    posterframe12.clearUi();
                    break;
                case 12:
                    int pos_for_update13;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update13 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe13.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update13);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update13 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe13.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update13);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }


                    posterframe13.clearUi();
                    break;
                case 13:
                    int pos_for_update14;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update14 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe14.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update14);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update14 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe14.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update14);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    posterframe14.clearUi();
                    break;
                case 14:
                    int pos_for_update15;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update15 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe15.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update15);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update15 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe15.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update15);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    posterframe15.clearUi();
                    break;
                case 15:
                    FinalImageModel finalImageModel16 = new FinalImageModel();
                    finalImageModel16.setType(Tags.type_one_page);
                    finalImageModel16.setImage1(getByteArrayFromBitmap(posterframe16.getBitmap()));
                    finalImageModel16.setFrame_type(Tags.Poster);
                    finalImageModel16.setPosition_on_frame(this.pos);
                    instance.addImage(finalImageModel16);
                    posterframe16.clearUi();
                    break;
                case 16:
                    int pos_for_update17;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update17 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe17.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update17);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update17 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe17.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update17);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }


                    posterframe17.clearUi();
                    break;
                case 17:
                    int pos_for_update18;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update18 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe18.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update18);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update18 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe18.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update18);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    posterframe18.clearUi();
                    break;

                case 18:
                    int pos_for_update19;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update19 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe19.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update19);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update19 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe19.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update19);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    posterframe19.clearUi();
                    break;

                case 19:
                    int pos_for_update20;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update20 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe20.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update20);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update20 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe20.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update20);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    posterframe20.clearUi();
                    break;
                case 20:
                    int pos_for_update21;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update21 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe21.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update21);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update21 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe21.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update21);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    posterframe21.clearUi();
                    break;

                case 21:
                    int pos_for_update22;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update22 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe22.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update22);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update22 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe22.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update22);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    posterframe22.clearUi();
                    break;


                case 22:
                    int pos_for_update24;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update24 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe24.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update24);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update24 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe24.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update24);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }

                    posterframe24.clearUi();
                    break;

                case 23:
                    int pos_for_update25;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update25 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(posterframe25.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update25);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update25 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(posterframe25.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_one_page);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.Poster);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update25);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }


                    posterframe25.clearUi();
                    break;

            }

        } else if (type.equals(Tags.twopager)) {


            switch (this.pos) {
                case 0:

                    int pos_for_update1;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update1 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(fragmentTwopagesShape1.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_two_pages);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.twopager);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update1);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update1 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(fragmentTwopagesShape1.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_two_pages);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.twopager);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update1);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }


                    fragmentTwopagesShape1.clearUi();
                    break;
                case 1:

                    int pos_for_update2;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update2 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(fragmentTwopagesShape2.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_two_pages);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.twopager);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update2);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update2 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(fragmentTwopagesShape2.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_two_pages);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.twopager);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update2);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }


                    fragmentTwopagesShape2.clearUi();
                    break;

                case 2:
                    int pos_for_update3;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update3 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(fragmentTwopagesShape3.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_two_pages);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.twopager);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update3);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update3 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(fragmentTwopagesShape3.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_two_pages);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.twopager);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update3);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);

                    }


                    fragmentTwopagesShape3.clearUi();
                    break;

                case 3:
                    int pos_for_update4;
                    if (which_image.equals("image1"))
                    {
                        byte [] image1 = finalImageModel_want_to_update.getImage1();
                        pos_for_update4 = instance.getItemPositionInImages(image1);

                        byte [] newImage1 = getByteArrayFromBitmap(fragmentTwopagesShape4.getBitmap());
                        finalImageModel_want_to_update.setImage1(newImage1);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_two_pages);
                        finalImageModel.setImage1(newImage1);
                        finalImageModel.setFrame_type(Tags.twopager);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update4);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);


                    }else if (which_image.equals("image2"))
                    {
                        byte [] image2 = finalImageModel_want_to_update.getImage2();
                        pos_for_update4 = instance.getItemPositionInImages(image2);
                        byte [] newImage2 = getByteArrayFromBitmap(fragmentTwopagesShape4.getBitmap());

                        finalImageModel_want_to_update.setImage2(newImage2);

                        FinalImageModel finalImageModel = new FinalImageModel();
                        finalImageModel.setType(Tags.type_two_pages);
                        finalImageModel.setImage1(newImage2);
                        finalImageModel.setFrame_type(Tags.twopager);
                        finalImageModel.setPosition_on_frame(this.pos);
                        instance.updateItemInImageList(finalImageModel,pos_for_update4);
                        ReturnNewDataBackToFinalActivity(finalImageModel_want_to_update);
                    }


                    fragmentTwopagesShape4.clearUi();
                    break;


            }

        }

    }

    private void ReturnNewDataBackToFinalActivity(FinalImageModel finalImageModel)
    {
        Intent intent = getIntent();
        setResult(RESULT_OK,intent);
        instance.setFinalImageModel_after_update(finalImageModel_want_to_update);
        UpdateFragmentUi();
        finish();
    }



    public void SetPos(int pos) {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        String uri = imageUrl.get(pos);

        if (type.equals(Tags.Classic)) {
            switch (this.pos) {
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
        } else if (type.equals(Tags.Pinboard)) {
            switch (this.pos) {
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
        } else if (type.equals(Tags.Poster)) {

            switch (this.pos) {
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

        } else if (type.equals(Tags.twopager)) {

            switch (this.pos) {
                case 0:
                    fragmentTwopagesShape1.getImageUri(uri);
                    break;
                case 1:
                    fragmentTwopagesShape2.getImageUri(uri);
                    break;
                case 2:
                    fragmentTwopagesShape3.getImageUri(uri);
                    break;
                case 3:
                    fragmentTwopagesShape4.getImageUri(uri);
                    break;

            }

        }


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMG_REQ && resultCode == RESULT_OK && data != null) {
            ClipData clipData = data.getClipData();
            if (clipData != null) {
                if (clipData.getItemCount() < 5 && imageUrl.size() == 0) {
                    android.support.v7.app.AlertDialog alertDialog = Common.chooseAlertDialog(UpdateImageActivity.this);
                    alertDialog.show();
                } else {
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        Uri uri = item.getUri();
                        String path = Common.getImagePath(UpdateImageActivity.this, uri);


                        imageUrl.add(path);

                    }
                    adapter.notifyDataSetChanged();

                    card_container.setVisibility(View.VISIBLE);
                }


            } else {


                if (imageUrl.size() >= 5) {
                    Uri uri = data.getData();
                    String path = Common.getImagePath(UpdateImageActivity.this, uri);

                    imageUrl.add(path);
                    adapter.notifyDataSetChanged();
                } else {

                    android.support.v7.app.AlertDialog alertDialog = Common.chooseAlertDialog(UpdateImageActivity.this);
                    alertDialog.show();

                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == read_req) {
            if (grantResults.length > 0) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    finish();
                }
            }
        }
    }

    public void setButtonsaveVisibility(int visibility) {
        switch (visibility) {
            case Tags.visible_btn:
                btn_save.setVisibility(View.VISIBLE);
                break;

            case Tags.invisible_btn:
                btn_save.setVisibility(View.GONE);

                break;
        }
    }




    private byte[] getByteArrayFromBitmap(Bitmap bitmap)

    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        return outputStream.toByteArray();
    }
}
