package com.Alatheer.Projects.lailaky.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.Alatheer.Projects.lailaky.Activites.FinalAlbumActivity;
import com.Alatheer.Projects.lailaky.R;

public class Fragment_Image_Album_Item extends Fragment{
    private static final String TAG = "TAG";
    private ImageView image,image_delete;
    private int pos ;

    private FinalAlbumActivity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_album_item,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        activity = (FinalAlbumActivity) getActivity();
        image = view.findViewById(R.id.image);
        image_delete = view.findViewById(R.id.image_delete);

        Bundle bundle = getArguments();
        if (bundle!=null)
        {
            byte [] bytes = bundle.getByteArray(TAG);
            UpdateUI(bytes);
        }
        image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.deletePage(pos);
            }
        });
    }

    private void UpdateUI(byte[] bytes) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        image.setImageBitmap(bitmap);
    }


    public static Fragment_Image_Album_Item getInstance(byte []  bitmap)
    {
        Fragment_Image_Album_Item fragment = new Fragment_Image_Album_Item();
        Bundle bundle = new Bundle();
        bundle.putByteArray(TAG,bitmap);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setPos(int pos)
    {
        this.pos = pos;
    }


}
