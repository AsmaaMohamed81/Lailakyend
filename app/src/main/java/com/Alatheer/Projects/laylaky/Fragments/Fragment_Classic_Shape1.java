package com.Alatheer.Projects.laylaky.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.Alatheer.Projects.laylaky.Activites.DisplayImagesActivity;
import com.Alatheer.Projects.laylaky.Activites.FramesActivity;
import com.Alatheer.Projects.laylaky.R;

import net.karthikraj.shapesimage.ShapesImage;

import java.io.FileNotFoundException;

public class Fragment_Classic_Shape1 extends Fragment implements View.OnTouchListener{
    private ShapesImage shape1,shape2,shape3,shape4,shape5;
    private ImageView shape1_icon,shape2_icon,shape3_icon,shape4_icon,shape5_icon;
    private Bitmap bitmap1,bitmap2,bitmap3,bitmap4,bitmap5;
    private final int IMG_REQ1=1,IMG_REQ2=2,IMG_REQ3=3,IMG_REQ4=4,IMG_REQ5=5;
    private FrameLayout f1,f2,f3,f4,f5;
    private DisplayImagesActivity activity;
    private int img1_selected = 0;
    private int img2_selected = 0;
    private int img3_selected = 0;
    private int img4_selected = 0;
    private int img5_selected = 0;




    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();
    // we can be in one of these 3 states
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    // remember some things for zooming
    private PointF start = new PointF();
    private PointF mid = new PointF();
    private float oldDist = 1f;
    private float d = 0f;
    private float newRot = 0f;
    private float[] lastEvent = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shap3_classic,container,false);
        initView(view);
        return view;
    }
    public static Fragment_Classic_Shape1 getInstance()
    {
        Fragment_Classic_Shape1 fragment = new Fragment_Classic_Shape1();
        return fragment;
    }
    private void initView(View view) {
        activity = (DisplayImagesActivity) getActivity();
        shape1 = view.findViewById(R.id.shape1);
        shape2 = view.findViewById(R.id.shape2);
        shape3 = view.findViewById(R.id.shape3);
        shape4 = view.findViewById(R.id.shape4);
        shape5 = view.findViewById(R.id.shape5);

        shape1_icon = view.findViewById(R.id.shape1_icon);
        shape2_icon = view.findViewById(R.id.shape2_icon);
        shape3_icon = view.findViewById(R.id.shape3_icon);
        shape4_icon = view.findViewById(R.id.shape4_icon);
        shape5_icon = view.findViewById(R.id.shape5_icon);

        f1 = view.findViewById(R.id.f1);
        f2 = view.findViewById(R.id.f2);
        f3 = view.findViewById(R.id.f3);
        f4 = view.findViewById(R.id.f4);
        f5 = view.findViewById(R.id.f5);

        shape1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmap1!=null&&bitmap2!=null&&bitmap3!=null&&bitmap4!=null&&bitmap5!=null)
                {
                    f1.setBackgroundResource(R.drawable.img_selected);
                    f2.setBackgroundResource(R.drawable.img_unselected);
                    f3.setBackgroundResource(R.drawable.img_unselected);
                    f4.setBackgroundResource(R.drawable.img_unselected);
                    f5.setBackgroundResource(R.drawable.img_unselected);
                    img1_selected = 1;
                    img2_selected = 0;
                    img3_selected = 0;
                    img4_selected = 0;
                    img5_selected = 0;
                   /* shape1.setEnabled(true);
                    shape2.setEnabled(false);
                    shape3.setEnabled(false);
                    shape4.setEnabled(false);
                    shape5.setEnabled(false);
*/
                }  else
                    {
                        SelectImage(IMG_REQ1);
                    }
            }
        });

        shape2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmap1!=null&&bitmap2!=null&&bitmap3!=null&&bitmap4!=null&&bitmap5!=null)
                {
                    f1.setBackgroundResource(R.drawable.img_unselected);
                    f2.setBackgroundResource(R.drawable.img_selected);
                    f3.setBackgroundResource(R.drawable.img_unselected);
                    f4.setBackgroundResource(R.drawable.img_unselected);
                    f5.setBackgroundResource(R.drawable.img_unselected);
                    img1_selected = 0;
                    img2_selected = 1;
                    img3_selected = 0;
                    img4_selected = 0;
                    img5_selected = 0;
                    shape1.setEnabled(false);
                    shape2.setEnabled(true);
                    shape3.setEnabled(false);
                    shape4.setEnabled(false);
                    shape5.setEnabled(false);
                }  else
                {
                    SelectImage(IMG_REQ2);
                }

            }
        });
        shape3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmap1!=null&&bitmap2!=null&&bitmap3!=null&&bitmap4!=null&&bitmap5!=null)
                {
                    f1.setBackgroundResource(R.drawable.img_unselected);
                    f2.setBackgroundResource(R.drawable.img_unselected);
                    f3.setBackgroundResource(R.drawable.img_selected);
                    f4.setBackgroundResource(R.drawable.img_unselected);
                    f5.setBackgroundResource(R.drawable.img_unselected);
                    img1_selected = 0;
                    img2_selected = 0;
                    img3_selected = 1;
                    img4_selected = 0;
                    img5_selected = 0;
//                    shape1.setEnabled(false);
//                    shape2.setEnabled(false);
//                    shape3.setEnabled(true);
//                    shape4.setEnabled(false);
//                    shape5.setEnabled(false);
                }  else
                {
                    SelectImage(IMG_REQ3);
                }

            }
        });
        shape4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmap1!=null&&bitmap2!=null&&bitmap3!=null&&bitmap4!=null&&bitmap5!=null)
                {
                    f1.setBackgroundResource(R.drawable.img_unselected);
                    f2.setBackgroundResource(R.drawable.img_unselected);
                    f3.setBackgroundResource(R.drawable.img_unselected);
                    f4.setBackgroundResource(R.drawable.img_selected);
                    f5.setBackgroundResource(R.drawable.img_unselected);
                    img1_selected = 0;
                    img2_selected = 0;
                    img3_selected = 0;
                    img4_selected = 1;
                    img5_selected = 0;
//                    shape1.setEnabled(false);
//                    shape2.setEnabled(false);
//                    shape3.setEnabled(false);
//                    shape4.setEnabled(true);
//                    shape5.setEnabled(false);

                }  else
                {
                    SelectImage(IMG_REQ4);
                }

            }
        });
        shape5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmap1!=null&&bitmap2!=null&&bitmap3!=null&&bitmap4!=null&&bitmap5!=null)
                {
                    f1.setBackgroundResource(R.drawable.img_unselected);
                    f2.setBackgroundResource(R.drawable.img_unselected);
                    f3.setBackgroundResource(R.drawable.img_unselected);
                    f4.setBackgroundResource(R.drawable.img_unselected);
                    f5.setBackgroundResource(R.drawable.img_selected);
                    img1_selected = 0;
                    img2_selected = 0;
                    img3_selected = 0;
                    img4_selected = 0;
                    img5_selected = 1;
//                    shape1.setEnabled(false);
//                    shape2.setEnabled(false);
//                    shape3.setEnabled(false);
//                    shape4.setEnabled(false);
//                    shape5.setEnabled(true);
                }  else
                {
                    SelectImage(IMG_REQ5);
                }


            }
        });


      /*  shape1.setOnTouchListener(this);
        shape2.setOnTouchListener(this);
        shape3.setOnTouchListener(this);
        shape4.setOnTouchListener(this);
        shape5.setOnTouchListener(this);
*/

    }

    private void SelectImage(int img_req)
    {
        activity.displayImage(img_req);
    }

    public void getImageUri(String uri)
    {
        try {
            Log.e("url",uri);
            Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(Uri.parse(uri)));
            if (bitmap1==null)
            {
                Log.e("url1","1");

                bitmap1 = bitmap;
                shape1.setImageBitmap(bitmap1);
                shape1_icon.setVisibility(View.GONE);
                f1.setBackgroundResource(R.drawable.img_selected);
                f2.setBackgroundResource(R.drawable.img_unselected);
                f3.setBackgroundResource(R.drawable.img_unselected);
                f4.setBackgroundResource(R.drawable.img_unselected);
                f5.setBackgroundResource(R.drawable.img_unselected);
                img1_selected = 1;
                img2_selected = 0;
                img3_selected = 0;
                img4_selected = 0;
                img5_selected = 0;
                shape1.setOnTouchListener(this);

//                shape1.setEnabled(true);
//                shape2.setEnabled(false);
//                shape3.setEnabled(false);
//                shape4.setEnabled(false);
//                shape5.setEnabled(false);

            }else if (bitmap2==null)
            {
                Log.e("url2","2");

                bitmap2 = bitmap;
                shape2.setImageBitmap(bitmap2);
                shape2_icon.setVisibility(View.GONE);
                f1.setBackgroundResource(R.drawable.img_unselected);
                f2.setBackgroundResource(R.drawable.img_selected);
                f3.setBackgroundResource(R.drawable.img_unselected);
                f4.setBackgroundResource(R.drawable.img_unselected);
                f5.setBackgroundResource(R.drawable.img_unselected);
                img1_selected = 0;
                img2_selected = 1;
                img3_selected = 0;
                img4_selected = 0;
                img5_selected = 0;
                shape2.setOnTouchListener(this);

//                shape1.setEnabled(false);
//                shape2.setEnabled(true);
//                shape3.setEnabled(false);
//                shape4.setEnabled(false);
//                shape5.setEnabled(false);
           }
            else if (bitmap3==null)
            {
                Log.e("url3","3");

                bitmap3 = bitmap;

                shape3.setImageBitmap(bitmap3);
                shape3_icon.setVisibility(View.GONE);
                f1.setBackgroundResource(R.drawable.img_unselected);
                f2.setBackgroundResource(R.drawable.img_unselected);
                f3.setBackgroundResource(R.drawable.img_selected);
                f4.setBackgroundResource(R.drawable.img_unselected);
                f5.setBackgroundResource(R.drawable.img_unselected);
                img1_selected = 0;
                img2_selected = 0;
                img3_selected = 1;
                img4_selected = 0;
                img5_selected = 0;
                shape3.setOnTouchListener(this);

//                shape1.setEnabled(false);
//                shape2.setEnabled(false);
//                shape3.setEnabled(true);
//                shape4.setEnabled(false);
//                shape5.setEnabled(false);
            }
            else if (bitmap4==null)
            {
                Log.e("url4","4");

                bitmap4 = bitmap;

                shape4.setImageBitmap(bitmap4);
                shape4_icon.setVisibility(View.GONE);
                f1.setBackgroundResource(R.drawable.img_unselected);
                f2.setBackgroundResource(R.drawable.img_unselected);
                f3.setBackgroundResource(R.drawable.img_unselected);
                f4.setBackgroundResource(R.drawable.img_selected);
                f5.setBackgroundResource(R.drawable.img_unselected);
                img1_selected = 0;
                img2_selected = 0;
                img3_selected = 0;
                img4_selected = 1;
                img5_selected = 0;
                shape4.setOnTouchListener(this);

//                shape1.setEnabled(false);
//                shape2.setEnabled(false);
//                shape3.setEnabled(false);
//                shape4.setEnabled(true);
//                shape5.setEnabled(false);
            }
            else if (bitmap5==null)
            {
                Log.e("url5","5");

                bitmap5 = bitmap;

                shape5.setImageBitmap(bitmap5);
                shape5_icon.setVisibility(View.GONE);
                f1.setBackgroundResource(R.drawable.img_unselected);
                f2.setBackgroundResource(R.drawable.img_unselected);
                f3.setBackgroundResource(R.drawable.img_unselected);
                f4.setBackgroundResource(R.drawable.img_unselected);
                f5.setBackgroundResource(R.drawable.img_selected);
                img1_selected = 0;
                img2_selected = 0;
                img3_selected = 0;
                img4_selected = 0;
                img5_selected = 1;
                shape5.setOnTouchListener(this);

//                shape1.setEnabled(false);
//                shape2.setEnabled(false);
//                shape3.setEnabled(false);
//                shape4.setEnabled(false);
//                shape5.setEnabled(true);
            }
            else if (bitmap1!=null&&bitmap2!=null&&bitmap3!=null&&bitmap4!=null&&bitmap5!=null)
            {
                Log.e("url6","6");

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
                else if (img5_selected==1)
                {

                    shape5.setImageBitmap(bitmap);

                }




            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public boolean onTouch(View v, MotionEvent event) {
        // handle touch events here
        ImageView view = (ImageView) v;
        int id = v.getId();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                mode = DRAG;
                lastEvent = null;
                if (id==R.id.shape1)
                {
                    f1.setBackgroundResource(R.drawable.img_selected);
                    f2.setBackgroundResource(R.drawable.img_unselected);
                    f3.setBackgroundResource(R.drawable.img_unselected);
                    f4.setBackgroundResource(R.drawable.img_unselected);
                    f5.setBackgroundResource(R.drawable.img_unselected);
                    shape1.setOnTouchListener(this);


                }else if (id==R.id.shape2)
                {
                    f1.setBackgroundResource(R.drawable.img_unselected);
                    f2.setBackgroundResource(R.drawable.img_selected);
                    f3.setBackgroundResource(R.drawable.img_unselected);
                    f4.setBackgroundResource(R.drawable.img_unselected);
                    f5.setBackgroundResource(R.drawable.img_unselected);

                    shape2.setOnTouchListener(this);
                }
                else if (id==R.id.shape3)
                {
                    f1.setBackgroundResource(R.drawable.img_unselected);
                    f2.setBackgroundResource(R.drawable.img_unselected);
                    f3.setBackgroundResource(R.drawable.img_selected);
                    f4.setBackgroundResource(R.drawable.img_unselected);
                    f5.setBackgroundResource(R.drawable.img_unselected);
                    shape3.setOnTouchListener(this);
                }
                else if (id==R.id.shape4)
                {
                    f1.setBackgroundResource(R.drawable.img_unselected);
                    f2.setBackgroundResource(R.drawable.img_unselected);
                    f3.setBackgroundResource(R.drawable.img_unselected);
                    f4.setBackgroundResource(R.drawable.img_selected);
                    f5.setBackgroundResource(R.drawable.img_unselected);


                    shape4.setOnTouchListener(this);
                }else if (id==R.id.shape5)
                {
                    f1.setBackgroundResource(R.drawable.img_unselected);
                    f2.setBackgroundResource(R.drawable.img_unselected);
                    f3.setBackgroundResource(R.drawable.img_unselected);
                    f4.setBackgroundResource(R.drawable.img_unselected);
                    f5.setBackgroundResource(R.drawable.img_selected);
                    shape5.setOnTouchListener(this);
                }
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

        view.setImageMatrix(matrix);
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

    /**
     * Calculate the degree to be rotated by.
     *
     * @param event
     * @return Degrees
     */
    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

}