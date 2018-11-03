package com.Alatheer.Projects.lailaky.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Alatheer.Projects.lailaky.ApiServices.Tags;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Classic;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Pinboard;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Poster;
import com.Alatheer.Projects.lailaky.R;
import com.github.siyamed.shapeimageview.RoundedImageView;

import java.util.List;

/**
 * Created by elashry on 10/07/2018.
 */

public class ClassicImagesAdapter extends RecyclerView.Adapter<ClassicImagesAdapter.MyHolder> {
    private Context context;
    private List<Integer> images;
    private String type="";
    private Fragment_Classic fragment_classic;
    private Fragment_Pinboard fragment_pinboard;
    private Fragment_Poster fragment_poster;
    public ClassicImagesAdapter(Context context, List<Integer> images,String type, Fragment fragment) {
        this.context = context;
        this.images = images;
        this.type = type;
        if (type.equals(Tags.Classic))
        {
            this.fragment_classic = (Fragment_Classic) fragment;
        }else if (type.equals(Tags.Pinboard))
        {
            this.fragment_pinboard = (Fragment_Pinboard) fragment;
        }else if (type.equals(Tags.Poster))
        {
            this.fragment_poster = (Fragment_Poster) fragment;
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.classic_image_row,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {
        int image = images.get(position);
        holder.BindData(image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals(Tags.Classic))
                {
                    fragment_classic.SetPos(holder.getAdapterPosition());
                    Log.e("Tag1adapter","classic");
                }else if (type.equals(Tags.Pinboard))
                {
                    fragment_pinboard.SetPos(holder.getAdapterPosition());
                    Log.e("Tag2adapter","pinboard");

                }else if (type.equals(Tags.Poster))
                {
                    fragment_poster.SetPos(holder.getAdapterPosition());

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        RoundedImageView img;
        public MyHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);

        }

        public void BindData(int image)
        {
            img.setImageResource(image);
        }
    }
}
