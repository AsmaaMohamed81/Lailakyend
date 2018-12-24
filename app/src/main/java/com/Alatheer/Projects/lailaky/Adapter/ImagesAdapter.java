package com.Alatheer.Projects.lailaky.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.Alatheer.Projects.lailaky.Activites.DisplayImagesActivity;
import com.Alatheer.Projects.lailaky.Activites.UpdateImageActivity;
import com.Alatheer.Projects.lailaky.R;

import java.util.List;

/**
 * Created by elashry on 10/07/2018.
 */

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.MyHolder> {
    private Context context;
    private List<String> imagesUri;
    private DisplayImagesActivity activity;
    private UpdateImageActivity updateImageActivity;
    private String type_of_context;

    public ImagesAdapter(Context context, List<String> imagesUri,String type_of_context) {
        this.context = context;
        this.imagesUri = imagesUri;
        this.type_of_context = type_of_context;
        if (type_of_context.equals("1"))
        {
            this.activity = (DisplayImagesActivity) context;

        }else if (type_of_context.equals("2"))
        {
            this.updateImageActivity = (UpdateImageActivity) context;

        }

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

                if (type_of_context.equals("1"))
                {
                    activity.SetPos(holder.getAdapterPosition());

                }else if (type_of_context.equals("2"))
                {
                    updateImageActivity.SetPos(holder.getAdapterPosition());

                }
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
            Bitmap bitmap = BitmapFactory.decodeFile(uri);
            img.setImageBitmap(bitmap);

            //Picasso.with(context).load(Uri.parse(uri)).into(img);
        }
    }
}
