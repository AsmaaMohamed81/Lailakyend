package com.Alatheer.Projects.laylaky.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Alatheer.Projects.laylaky.Activites.DisplayImagesActivity;
import com.Alatheer.Projects.laylaky.Adapter.ClassicImagesAdapter;
import com.Alatheer.Projects.laylaky.ApiServices.Tags;
import com.Alatheer.Projects.laylaky.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elashry on 09/07/2018.
 */

public class Fragment_Poster extends Fragment {
    private static final String TAG1="user_id";
    private static final String TAG2="offer_id";
    private static final String TAG3="album_size";
    private String user_id="",offer_id="";
    private int album_size=0;


    private RecyclerView recView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter adapter;
    private List<Integer> Images;
    private Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poster,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        Bundle bundle = getArguments();

        if (bundle!=null)
        {
            user_id = bundle.getString(TAG1);
            offer_id = bundle.getString(TAG2);
            album_size = bundle.getInt(TAG3);
        }
        Images = new ArrayList<>();
        context =view.getContext();
        recView = view.findViewById(R.id.recView);
        manager = new GridLayoutManager(context,2);
        recView.setLayoutManager(manager);
        adapter = new ClassicImagesAdapter(context,Images, Tags.Poster,this);
        recView.setAdapter(adapter);
        AddImages();
    }

    private void AddImages() {
        Images.add(R.drawable.f15);
        Images.add(R.drawable.f16);
        Images.add(R.drawable.f14);
        Images.add(R.drawable.f18);

        Images.add(R.drawable.f19);
        Images.add(R.drawable.f17);
        Images.add(R.drawable.f11);
        Images.add(R.drawable.f12);

        Images.add(R.drawable.f13);
        Images.add(R.drawable.f6);
        Images.add(R.drawable.f4);
        Images.add(R.drawable.f23);

        Images.add(R.drawable.f25);
        Images.add(R.drawable.f9);
        Images.add(R.drawable.f10);
        Images.add(R.drawable.f20);

        Images.add(R.drawable.f22);
        Images.add(R.drawable.f24);
        Images.add(R.drawable.f7);
        Images.add(R.drawable.f2);

        Images.add(R.drawable.f28);
        Images.add(R.drawable.f5);
        Images.add(R.drawable.f26);
        Images.add(R.drawable.f27);

        Images.add(R.drawable.f8);
        Images.add(R.drawable.f21);

        adapter.notifyDataSetChanged();
    }

    public static Fragment_Poster getInstance(String user_id,String offer_id,int album_size)
    {
        Fragment_Poster fragment = new Fragment_Poster();
        Bundle bundle = new Bundle();
        bundle.putString(TAG1,user_id);
        bundle.putString(TAG2,offer_id);
        bundle.putInt(TAG3,album_size);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void SetPos(int pos)
    {


        Intent intent = new Intent(getActivity(), DisplayImagesActivity.class);
        intent.putExtra("position",pos);
        intent.putExtra("type",Tags.Classic);
        context.startActivity(intent);
    }
}
