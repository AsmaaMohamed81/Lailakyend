package com.Alatheer.Projects.laylaky.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.Alatheer.Projects.laylaky.Activites.DisplayImagesActivity;
import com.Alatheer.Projects.laylaky.R;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by elashry on 10/07/2018.
 */

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.MyHolder> {
    private Context context;
    private List<String> imagesUri;
    private DisplayImagesActivity activity;

    public ImagesAdapter(Context context, List<String> imagesUri) {
        this.context = context;
        this.imagesUri = imagesUri;
        this.activity = (DisplayImagesActivity) context;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_row,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {
        String uri = imagesUri.get(position);
        holder.BindData(uri);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.SetPos(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return imagesUri.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public MyHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);

        }

        public void BindData(String uri)
        {
            Picasso.with(context).load(Uri.parse(uri)).into(img);
        }
    }
}
