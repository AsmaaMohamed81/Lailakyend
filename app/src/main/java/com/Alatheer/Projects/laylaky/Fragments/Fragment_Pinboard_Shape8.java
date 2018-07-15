package com.Alatheer.Projects.laylaky.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

public class Fragment_Pinboard_Shape8 extends Fragment implements View.OnTouchListener{
    private ShapesImage shape1,shape2,shape3;
    private ImageView shape1_icon,shape2_icon,shape3_icon;
    private Bitmap bitmap1,bitmap2,bitmap3;
    private final int IMG_REQ1=1,IMG_REQ2=2,IMG_REQ3=3;
    private FrameLayout f1,f2,f3;
    private DisplayImagesActivity activity;
    private int img1_selected = 0;
    private int img2_selected = 0;
    private int img3_selected = 0;

    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();
    // we can be in one of these 3 states
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    private PointF start = new PointF();
    private PointF mid = new PointF();
    private float oldDist = 1f;
    private float d = 0f;
    private float newRot = 0f;
    private float[] lastEvent = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shape18_pinboard,container,false);
        initView(view);
        return view;
    }
    private void initView(View view) {
        activity = (DisplayImagesActivity) getActivity();
        shape1 = view.findViewById(R.id.shape1);
        shape2 = view.findViewById(R.id.shape2);
        shape3 = view.findViewById(R.id.shape3);

        shape1_icon = view.findViewById(R.id.shape1_icon);
        shape2_icon = view.findViewById(R.id.shape2_icon);
        shape3_icon = view.findViewById(R.id.shape3_icon);


        f1 = view.findViewById(R.id.f1);
        f2 = view.findViewById(R.id.f2);
        f3 = view.findViewById(R.id.f3);


        shape1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmap1!=null&&bitmap2!=null&&bitmap3!=null)
                {
                    f1.setBackgroundResource(R.drawable.img_selected);
                    f2.setBackgroundResource(R.drawable.img_unselected);
                    f3.setBackgroundResource(R.drawable.img_unselected);

                    img1_selected = 1;
                    img2_selected = 0;
                    img3_selected = 0;


                }  else
                {
                    SelectImage(IMG_REQ1);
                }
            }
        });

        shape2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmap1!=null&&bitmap2!=null&&bitmap3!=null)
                {
                    f1.setBackgroundResource(R.drawable.img_unselected);
                    f2.setBackgroundResource(R.drawable.img_selected);
                    f3.setBackgroundResource(R.drawable.img_unselected);

                    img1_selected = 0;
                    img2_selected = 1;
                    img3_selected = 0;


                }  else
                {
                    SelectImage(IMG_REQ2);
                }

            }
        });
        shape3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmap1!=null&&bitmap2!=null&&bitmap3!=null)
                {
                    f1.setBackgroundResource(R.drawable.img_unselected);
                    f2.setBackgroundResource(R.drawable.img_unselected);
                    f3.setBackgroundResource(R.drawable.img_selected);

                    img1_selected = 0;
                    img2_selected = 0;
                    img3_selected = 1;


                }  else
                {
                    SelectImage(IMG_REQ3);
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

                img1_selected = 1;
                img2_selected = 0;
                img3_selected = 0;
                shape1.setOnTouchListener(this);



            }else if (bitmap2==null)
            {

                bitmap2 = bitmap;
                shape2.setImageBitmap(bitmap2);
                shape2_icon.setVisibility(View.GONE);
                f1.setBackgroundResource(R.drawable.img_unselected);
                f2.setBackgroundResource(R.drawable.img_selected);
                f3.setBackgroundResource(R.drawable.img_unselected);

                img1_selected = 0;
                img2_selected = 1;
                img3_selected = 0;
                shape2.setOnTouchListener(this);


            }
            else if (bitmap3==null)
            {

                bitmap3 = bitmap;

                shape3.setImageBitmap(bitmap3);
                shape3_icon.setVisibility(View.GONE);
                f1.setBackgroundResource(R.drawable.img_unselected);
                f2.setBackgroundResource(R.drawable.img_unselected);
                f3.setBackgroundResource(R.drawable.img_selected);
                shape3.setOnTouchListener(this);

                img1_selected = 0;
                img2_selected = 0;
                img3_selected = 1;

            } else if (bitmap1!=null&&bitmap2!=null&&bitmap3!=null)
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



            }




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Fragment_Pinboard_Shape8 getInstance()
    {
        Fragment_Pinboard_Shape8 fragment = new Fragment_Pinboard_Shape8();
        return fragment;
    }
    public boolean onTouch(View v, MotionEvent event) {
        ImageView view = (ImageView) v;

        int id = v.getId();

        switch (id)
        {
            case R.id.shape1:
                f1.setBackgroundResource(R.drawable.img_selected);
                f2.setBackgroundResource(R.drawable.img_unselected);
                f3.setBackgroundResource(R.drawable.img_unselected);

                img1_selected = 1;
                img2_selected = 0;
                img3_selected = 0;

                break;
            case R.id.shape2:
                f1.setBackgroundResource(R.drawable.img_unselected);
                f2.setBackgroundResource(R.drawable.img_selected);
                f3.setBackgroundResource(R.drawable.img_unselected);

                img1_selected = 0;
                img2_selected = 1;
                img3_selected = 0;

                break;
            case R.id.shape3:
                f1.setBackgroundResource(R.drawable.img_unselected);
                f2.setBackgroundResource(R.drawable.img_unselected);
                f3.setBackgroundResource(R.drawable.img_selected);

                img1_selected = 0;
                img2_selected = 0;
                img3_selected = 1;

                break;
            case R.id.shape4:
                f1.setBackgroundResource(R.drawable.img_unselected);
                f2.setBackgroundResource(R.drawable.img_unselected);
                f3.setBackgroundResource(R.drawable.img_unselected);

                img1_selected = 0;
                img2_selected = 0;
                img3_selected = 0;

                break;
            case R.id.shape5:
                f1.setBackgroundResource(R.drawable.img_unselected);
                f2.setBackgroundResource(R.drawable.img_unselected);
                f3.setBackgroundResource(R.drawable.img_unselected);

                img1_selected = 0;
                img2_selected = 0;
                img3_selected = 0;

                break;

        }
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                mode = DRAG;
                lastEvent = null;

                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > 10f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                }
                lastEvent = new float[4];
                lastEvent[0] = event.getX(0);
                lastEvent[1] = event.getX(1);
                lastEvent[2] = event.getY(0);
                lastEvent[3] = event.getY(1);
                d = rotation(event);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                lastEvent = null;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    matrix.set(savedMatrix);
                    float dx = event.getX() - start.x;
                    float dy = event.getY() - start.y;
                    matrix.postTranslate(dx, dy);
                } else if (mode == ZOOM) {
                    float newDist = spacing(event);
                    if (newDist > 10f) {
                        matrix.set(savedMatrix);
                        float scale = (newDist / oldDist);
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                    if (lastEvent != null && event.getPointerCount() == 2) {
                        newRot = rotation(event);
                        float r = newRot - d;
                        float[] values = new float[9];
                        matrix.getValues(values);
                        float tx = values[2];
                        float ty = values[5];
                        float sx = values[0];
                        float xc = (view.getWidth() / 2) * sx;
                        float yc = (view.getHeight() / 2) * sx;
                        matrix.postRotate(r, tx + xc, ty + yc);
                    }
                }
                break;
        }

        if (img1_selected==1)
        {
            shape1.setImageMatrix(matrix);

        }else if (img2_selected==1)
        {
            shape2.setImageMatrix(matrix);

        }
        else if (img3_selected==1)
        {
            shape3.setImageMatrix(matrix);

        }


        return true;
    }


    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return ( float) Math.sqrt(x * x + y * y);
    }




    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }


    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }
}
