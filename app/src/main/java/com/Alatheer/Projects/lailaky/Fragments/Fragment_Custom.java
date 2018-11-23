package com.Alatheer.Projects.lailaky.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.Alatheer.Projects.lailaky.Activites.DisplayImagesActivity;
import com.Alatheer.Projects.lailaky.ApiServices.Tags;
import com.Alatheer.Projects.lailaky.R;
import com.jcmore2.collage.CollageView;

/**
 * Created by elashry on 09/07/2018.
 */

public class Fragment_Custom extends Fragment {
    private static final String TAG1="user_id";
    private static final String TAG2="offer_id";
    private static final String TAG3="album_size";
    private static final String TAG4="paper_id";

    private String user_id="",offer_id="",paper_id="";
    private int album_size=0;
    private CollageView collageView;
    private ImageView select_btn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom,container,false);
        initView(view);
        return view;
    }


    private void initView(View view) {
        Bundle bundle = getArguments();


        if (bundle!=null)
        {
            user_id = bundle.getString(TAG1);
            offer_id = bundle.getString(TAG2);
            paper_id = bundle.getString(TAG4);
            album_size = bundle.getInt(TAG3);
          //  Toast.makeText(getActivity(), ""+paper_id, Toast.LENGTH_SHORT).show();

        }

        collageView = view.findViewById(R.id.collageView);
        select_btn = view.findViewById(R.id.select_btn);
        select_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("sdasd","453543453435");
                Intent intent = new Intent(getActivity(), DisplayImagesActivity.class);
                intent.putExtra("type", Tags.custom);
                intent.putExtra("position",555);
                intent.putExtra("album_size",album_size);
                intent.putExtra("user_id",user_id);
                intent.putExtra("id_offer",offer_id);
                intent.putExtra("paper_id",paper_id);
                startActivity(intent);
            }
        });

    }

    public static Fragment_Custom getInstance(String user_id,String offer_id,String paper_id,int album_size)
    {
        Fragment_Custom fragment = new Fragment_Custom();
        Bundle bundle = new Bundle();
        bundle.putString(TAG1,user_id);
        bundle.putString(TAG2,offer_id);
        bundle.putString(TAG4,paper_id);
        bundle.putInt(TAG3,album_size);
        fragment.setArguments(bundle);
        return fragment;
    }
}
