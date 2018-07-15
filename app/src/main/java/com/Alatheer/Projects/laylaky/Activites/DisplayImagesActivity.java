package com.Alatheer.Projects.laylaky.Activites;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Alatheer.Projects.laylaky.Adapter.ImagesAdapter;
import com.Alatheer.Projects.laylaky.ApiServices.Tags;
import com.Alatheer.Projects.laylaky.Fragments.Fragment_Classic_Shape1;
import com.Alatheer.Projects.laylaky.Fragments.Fragment_Pinboard_Shape9;
import com.Alatheer.Projects.laylaky.Fragments.Fragment_Classic_Shape2;
import com.Alatheer.Projects.laylaky.Fragments.Fragment_Classic_Shape3;
import com.Alatheer.Projects.laylaky.Fragments.Fragment_Classic_Shape4;
import com.Alatheer.Projects.laylaky.Fragments.Fragment_Classic_Shape5;
import com.Alatheer.Projects.laylaky.Fragments.Fragment_Classic_Shape6;
import com.Alatheer.Projects.laylaky.Fragments.Fragment_Classic_Shape7;
import com.Alatheer.Projects.laylaky.Fragments.Fragment_Classic_Shape8;
import com.Alatheer.Projects.laylaky.Fragments.Fragment_Classic_Shape9;
import com.Alatheer.Projects.laylaky.Fragments.Fragment_Pinboard_Shape1;
import com.Alatheer.Projects.laylaky.Fragments.Fragment_Pinboard_Shape2;
import com.Alatheer.Projects.laylaky.Fragments.Fragment_Pinboard_Shape3;
import com.Alatheer.Projects.laylaky.Fragments.Fragment_Pinboard_Shape4;
import com.Alatheer.Projects.laylaky.Fragments.Fragment_Pinboard_Shape5;
import com.Alatheer.Projects.laylaky.Fragments.Fragment_Pinboard_Shape6;
import com.Alatheer.Projects.laylaky.Fragments.Fragment_Pinboard_Shape7;
import com.Alatheer.Projects.laylaky.Fragments.Fragment_Pinboard_Shape8;
import com.Alatheer.Projects.laylaky.R;

import java.util.ArrayList;
import java.util.List;

public class DisplayImagesActivity extends AppCompatActivity {
    private RecyclerView recView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter adapter;
    private List<String> imageUrl;
    private  int IMG_REQ;
    private String type="";
    private int pos=0;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_images);
        initView();
    }

    public void displayImage(int img_req) {
        IMG_REQ = img_req;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        startActivityForResult(intent.createChooser(intent,"Choose image"),IMG_REQ);


    }

    private void initView() {
        getDataFromIntent();
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
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent!=null)
        {
            type = intent.getStringExtra("type");
            pos = intent.getIntExtra("position",0);

            UpdateUi(type,pos);
        }
    }

    private void UpdateUi(String type, int pos) {
        Log.e("type",type);
        Log.e("pos",pos+"");
        if (type.equals(Tags.Classic))
        {
            switch (pos)
            {
                case 0:
                    classicShape1 = Fragment_Classic_Shape1.getInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,classicShape1).commit();
                    break;
                case 1:
                    classicShape2 = Fragment_Classic_Shape2.getInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape2).commit();

                    break;
                case 2:
                    classicShape3 = Fragment_Classic_Shape3.getInstance();

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape3).commit();

                    break;
                case 3:
                    classicShape4 = Fragment_Classic_Shape4.getInstance();

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape4).commit();

                    break;
                case 4:
                    classicShape5 = Fragment_Classic_Shape5.getInstance();

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape5).commit();

                    break;
                case 5:
                    classicShape6 = Fragment_Classic_Shape6.getInstance();

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape6).commit();

                    break;
                case 6:
                    classicShape7 = Fragment_Classic_Shape7.getInstance();

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape7).commit();

                    break;
                case 7:
                    classicShape8 = Fragment_Classic_Shape8.getInstance();

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape8).commit();

                    break;
                case 8:
                    classicShape9 = Fragment_Classic_Shape9.getInstance();

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classicShape9).commit();

                    break;


            }
        }else if (type.equals(Tags.Pinboard))
        {
            switch (pos)
            {
                case 0:
                    pinboardShape1 = Fragment_Pinboard_Shape1.getInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape1).commit();
                    break;
                case 1:
                    pinboardShape2 = Fragment_Pinboard_Shape2.getInstance();

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape2).commit();

                    break;
                case 2:
                    pinboardShape3 = Fragment_Pinboard_Shape3.getInstance();

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape3).commit();

                    break;
                case 3:
                    pinboardShape4 = Fragment_Pinboard_Shape4.getInstance();

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape4).commit();

                    break;
                case 4:
                    pinboardShape5 = Fragment_Pinboard_Shape5.getInstance();

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape5).commit();

                    break;
                case 5:
                    pinboardShape6 = Fragment_Pinboard_Shape6.getInstance();

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape6).commit();

                    break;
                case 6:
                    pinboardShape7 = Fragment_Pinboard_Shape7.getInstance();

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape7).commit();

                    break;
                case 7:
                    pinboardShape8 = Fragment_Pinboard_Shape8.getInstance();

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape8).commit();

                    break;
                case 8:
                    pinboardShape9 = Fragment_Pinboard_Shape9.getInstance();

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pinboardShape9).commit();

                    break;




            }
        }else if (type.equals(Tags.Poster))
        {

        }else if (type.equals(Tags.custom))
        {
           Intent intent = new Intent(DisplayImagesActivity.this,CustomShapeActivity.class);
           startActivity(intent);
           finish();
        }
    }

    public void SetPos(int pos)
    {
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
                    Toast.makeText(this, "اختر على الاقل 5 صور", Toast.LENGTH_SHORT).show();

                }else
                    {
                        for (int i =0;i<clipData.getItemCount();i++)
                        {
                            ClipData.Item item = clipData.getItemAt(i);
                            String uri = item.getUri().toString();
                            imageUrl.add(uri);

                        }
                        adapter.notifyDataSetChanged();

                        card_container.setVisibility(View.VISIBLE);
                    }


            }else
                {
                    Uri uri = data.getData();
                    imageUrl.add(uri.toString());
                    adapter.notifyDataSetChanged();
                    card_container.setVisibility(View.VISIBLE);

                    /*if (imageUrl.size()>=5)
                    {
                        Uri uri = data.getData();
                        imageUrl.add(uri.toString());
                        adapter.notifyDataSetChanged();
                    }else
                        {
                            Toast.makeText(this, "اختر على الاقل 5 صور", Toast.LENGTH_SHORT).show();

                        }*/
                }
        }
    }


}
