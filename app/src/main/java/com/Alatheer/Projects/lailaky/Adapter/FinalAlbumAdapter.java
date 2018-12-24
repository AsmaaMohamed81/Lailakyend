package com.Alatheer.Projects.lailaky.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.Alatheer.Projects.lailaky.Activites.FinalAlbumActivity;
import com.Alatheer.Projects.lailaky.R;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

/**
 * Created by elashry on 10/07/2018.
 */

public class FinalAlbumAdapter extends RecyclerView.Adapter<FinalAlbumAdapter.MyHolder> {
    private Context context;
    private List<Bitmap> bitmapList;
    private FinalAlbumActivity activity;

    public FinalAlbumAdapter(Context context, List<Bitmap> bitmapList) {
        this.context = context;
        this.bitmapList = bitmapList;
        this.activity = (FinalAlbumActivity) context;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.final_album_image_row,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {
        Bitmap bitmap = bitmapList.get(position);
        holder.BindData(bitmap);
        if (holder.checkBox.isChecked())
        {
            holder.checkBox.setChecked(false);
        }
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //activity.setBitmapForDelete(holder.getAdapterPosition(),holder.checkBox);
            }
        });


    }

    @Override
    public int getItemCount() {
        return bitmapList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private PhotoView img;
        private CheckBox checkBox;
        public MyHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image);
            checkBox = itemView.findViewById(R.id.checkbox);
        }

        public void BindData(Bitmap bitmap)
        {
            img.setImageBitmap(bitmap);
        }
    }
}
