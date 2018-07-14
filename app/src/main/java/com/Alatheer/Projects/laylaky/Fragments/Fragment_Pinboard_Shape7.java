package com.Alatheer.Projects.laylaky.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.Alatheer.Projects.laylaky.Activites.DisplayImagesActivity;
import com.Alatheer.Projects.laylaky.R;

import net.karthikraj.shapesimage.ShapesImage;

import java.io.FileNotFoundException;

/**
 * Created by elashry on 10/07/2018.
 */

public class Fragment_Pinboard_Shape7 extends Fragment {
    private ShapesImage shape1;
    private ImageView shape1_icon;
    private Bitmap bitmap1;
    private final int IMG_REQ1=1;
    private FrameLayout f1;
    private DisplayImagesActivity activity;
    private int img1_selected = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shap9_pinboard,container,false);
        initView(view);
        return view;
    }
    private void initView(View view) {
        activity = (DisplayImagesActivity) getActivity();
        shape1 = view.findViewById(R.id.shape1);


        shape1_icon = view.findViewById(R.id.shape1_icon);

        f1 = view.findViewById(R.id.f1);


        shape1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmap1!=null)
                {
                    f1.setBackgroundResource(R.drawable.img_selected);
                    img1_selected = 1;


                }  else
                {
                    SelectImage(IMG_REQ1);
                }
            }
        });



    }

    private void SelectImage(int img_req)
    {
        activity.displayImage(img_req);
    }

    public void getImageUri(String uri)
    {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(Uri.parse(uri)));
            if (bitmap1==null)
            {

                bitmap1 = bitmap;
                shape1.setImageBitmap(bitmap1);
                shape1_icon.setVisibility(View.GONE);
                f1.setBackgroundResource(R.drawable.img_selected);

                img1_selected = 1;



            }

            else if (bitmap1!=null)
            {

                if (img1_selected==1)
                {
                    shape1.setImageBitmap(bitmap);

                }


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Fragment_Pinboard_Shape7 getInstance()
    {
        Fragment_Pinboard_Shape7 fragment = new Fragment_Pinboard_Shape7();
        return fragment;
    }
}
