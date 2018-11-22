package com.Alatheer.Projects.lailaky.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.Alatheer.Projects.lailaky.Activites.DisplayImagesActivity;
import com.Alatheer.Projects.lailaky.Adapter.ClassicImagesAdapter;
import com.Alatheer.Projects.lailaky.ApiServices.Tags;
import com.Alatheer.Projects.lailaky.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elashry on 09/07/2018.
 */

public class Fragment_Classic extends Fragment {
    private static final String TAG1="user_id";
    private static final String TAG2="offer_id";
    private static final String TAG3="album_size";
    private static final String TAG4="paper_id";
    private String user_id="",offer_id="",paper_id="";
    private int album_size=0;
    private RecyclerView recView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter adapter;
    private List<Integer> Images;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classic,container,false);
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


            Log.e("fclassic",album_size+"");
            Log.e("fclassic",user_id+"");
            Log.e("fclassic",offer_id+"");
            Toast.makeText(context, ""+paper_id, Toast.LENGTH_SHORT).show();
        }
        Images = new ArrayList<>();
        context =view.getContext();
        recView = view.findViewById(R.id.recView);
        manager = new GridLayoutManager(context,2);
        recView.setLayoutManager(manager);
        adapter = new ClassicImagesAdapter(context,Images, Tags.Classic,this);
        recView.setAdapter(adapter);
        AddImages();
    }

    private void AddImages() {
        Images.add(R.drawable.a3);
        Images.add(R.drawable.a6);
        Images.add(R.drawable.a10);
        Images.add(R.drawable.a11);
        Images.add(R.drawable.a12);
        Images.add(R.drawable.a13);
        Images.add(R.drawable.a14);
        Images.add(R.drawable.a15);
        Images.add(R.drawable.a16);

        adapter.notifyDataSetChanged();
    }

    public static Fragment_Classic getInstance(String user_id,String offer_id,String paper_id,int album_size)
    {
        Fragment_Classic fragment = new Fragment_Classic();
        Bundle bundle = new Bundle();
        bundle.putString(TAG1,user_id);
        bundle.putString(TAG2,offer_id);
        bundle.putString(TAG4,paper_id);
        bundle.putInt(TAG3,album_size);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void SetPos(int pos)
    {


        Intent intent = new Intent(getActivity(), DisplayImagesActivity.class);
        intent.putExtra("position",pos);
        intent.putExtra("type",Tags.Classic);
        intent.putExtra("album_size",album_size);
        intent.putExtra("user_id",user_id);
        intent.putExtra("id_offer",offer_id);

        context.startActivity(intent);
    }
}
