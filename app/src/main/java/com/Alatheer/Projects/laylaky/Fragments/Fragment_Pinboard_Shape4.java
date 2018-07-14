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

public class Fragment_Pinboard_Shape4 extends Fragment {
    private ShapesImage shape1,shape2,shape3,shape4;
    private ImageView shape1_icon,shape2_icon,shape3_icon,shape4_icon;
    private Bitmap bitmap1,bitmap2,bitmap3,bitmap4;
    private final int IMG_REQ1=1,IMG_REQ2=2,IMG_REQ3=3,IMG_REQ4=4;
    private FrameLayout f1,f2,f3,f4;
    private DisplayImagesActivity activity;
    private int img1_selected = 0;
    private int img2_selected = 0;
    private int img3_selected = 0;
    private int img4_selected = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shap5_pinboard,container,false);
        initView(view);
        return view;
    }
    private void initView(View view) {
        activity = (DisplayImagesActivity) getActivity();
        shape1 = view.findViewById(R.id.shape1);
        shape2 = view.findViewById(R.id.shape2);
        shape3 = view.findViewById(R.id.shape3);
        shape4 = view.findViewById(R.id.shape4);

        shape1_icon = view.findViewById(R.id.shape1_icon);
        shape2_icon = view.findViewById(R.id.shape2_icon);
        shape3_icon = view.findViewById(R.id.shape3_icon);
        shape4_icon = view.findViewById(R.id.shape4_icon);

        f1 = view.findViewById(R.id.f1);
        f2 = view.findViewById(R.id.f2);
        f3 = view.findViewById(R.id.f3);
        f4 = view.findViewById(R.id.f4);

        shape1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmap1!=null&&bitmap2!=null&&bitmap3!=null&&bitmap4!=null)
                {
                    f1.setBackgroundResource(R.drawable.img_selected);
                    f2.setBackgroundResource(R.drawable.img_unselected);
                    f3.setBackgroundResource(R.drawable.img_unselected);
                    f4.setBackgroundResource(R.drawable.img_unselected);
                    img1_selected = 1;
                    img2_selected = 0;
                    img3_selected = 0;
                    img4_selected = 0;

                }  else
                {
                    SelectImage(IMG_REQ1);
                }
            }
        });

        shape2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmap1!=null&&bitmap2!=null&&bitmap3!=null&&bitmap4!=null)
                {
                    f1.setBackgroundResource(R.drawable.img_unselected);
                    f2.setBackgroundResource(R.drawable.img_selected);
                    f3.setBackgroundResource(R.drawable.img_unselected);
                    f4.setBackgroundResource(R.drawable.img_unselected);
                    img1_selected = 0;
                    img2_selected = 1;
                    img3_selected = 0;
                    img4_selected = 0;

                }  else
                {
                    SelectImage(IMG_REQ2);
                }

            }
        });
        shape3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmap1!=null&&bitmap2!=null&&bitmap3!=null&&bitmap4!=null)
                {
                    f1.setBackgroundResource(R.drawable.img_unselected);
                    f2.setBackgroundResource(R.drawable.img_unselected);
                    f3.setBackgroundResource(R.drawable.img_selected);
                    f4.setBackgroundResource(R.drawable.img_unselected);
                    img1_selected = 0;
                    img2_selected = 0;
                    img3_selected = 1;
                    img4_selected = 0;

                }  else
                {
                    SelectImage(IMG_REQ3);
                }

            }
        });
        shape4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmap1!=null&&bitmap2!=null&&bitmap3!=null&&bitmap4!=null)
                {
                    f1.setBackgroundResource(R.drawable.img_unselected);
                    f2.setBackgroundResource(R.drawable.img_unselected);
                    f3.setBackgroundResource(R.drawable.img_unselected);
                    f4.setBackgroundResource(R.drawable.img_selected);
                    img1_selected = 0;
                    img2_selected = 0;
                    img3_selected = 0;
                    img4_selected = 1;

                }  else
                {
                    SelectImage(IMG_REQ4);
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
                f2.setBackgroundResource(R.drawable.img_unselected);
                f3.setBackgroundResource(R.drawable.img_unselected);
                f4.setBackgroundResource(R.drawable.img_unselected);
                img1_selected = 1;
                img2_selected = 0;
                img3_selected = 0;
                img4_selected = 0;


            }else if (bitmap2==null)
            {

                bitmap2 = bitmap;
                shape2.setImageBitmap(bitmap2);
                shape2_icon.setVisibility(View.GONE);
                f1.setBackgroundResource(R.drawable.img_unselected);
                f2.setBackgroundResource(R.drawable.img_selected);
                f3.setBackgroundResource(R.drawable.img_unselected);
                f4.setBackgroundResource(R.drawable.img_unselected);
                img1_selected = 0;
                img2_selected = 1;
                img3_selected = 0;
                img4_selected = 0;
            }
            else if (bitmap3==null)
            {

                bitmap3 = bitmap;

                shape3.setImageBitmap(bitmap3);
                shape3_icon.setVisibility(View.GONE);
                f1.setBackgroundResource(R.drawable.img_unselected);
                f2.setBackgroundResource(R.drawable.img_unselected);
                f3.setBackgroundResource(R.drawable.img_selected);
                f4.setBackgroundResource(R.drawable.img_unselected);
                img1_selected = 0;
                img2_selected = 0;
                img3_selected = 1;
                img4_selected = 0;
            }
            else if (bitmap4==null)
            {

                bitmap4 = bitmap;

                shape4.setImageBitmap(bitmap4);
                shape4_icon.setVisibility(View.GONE);
                f1.setBackgroundResource(R.drawable.img_unselected);
                f2.setBackgroundResource(R.drawable.img_unselected);
                f3.setBackgroundResource(R.drawable.img_unselected);
                f4.setBackgroundResource(R.drawable.img_selected);
                img1_selected = 0;
                img2_selected = 0;
                img3_selected = 0;
                img4_selected = 1;
            }

            else if (bitmap1!=null&&bitmap2!=null&&bitmap3!=null&&bitmap4!=null)
            {

                if (img1_selected==1)
                {
                    shape1.setImageBitmap(bitmap);

                }else if (img2_selected==1)
                {
                    shape2.setImageBitmap(bitmap);

                }
                else if (img3_selected==1)
                {
                    shape3.setImageBitmap(bitmap);

                }
                else if (img4_selected==1)
                {
                    shape4.setImageBitmap(bitmap);

                }



            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Fragment_Pinboard_Shape4 getInstance()
    {
        Fragment_Pinboard_Shape4 fragment = new Fragment_Pinboard_Shape4();
        return fragment;
    }
}
