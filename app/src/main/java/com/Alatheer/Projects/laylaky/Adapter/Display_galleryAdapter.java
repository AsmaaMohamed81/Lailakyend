package com.Alatheer.Projects.laylaky.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.Alatheer.Projects.laylaky.Activites.DisplayGalleryActivity;
import com.Alatheer.Projects.laylaky.Models.GalleryModel;
import com.Alatheer.Projects.laylaky.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emad on 2018-04-14.
 */

public class Display_galleryAdapter extends RecyclerView.Adapter<Display_galleryAdapter.myHolder> {

    private Context context;
    private List<Bitmap> galleryModelList;
    private List<Bitmap> deleted;
    private DisplayGalleryActivity activity;
    public Display_galleryAdapter(Context context, List<Bitmap> galleryModelList) {
        this.context = context;
        this.activity = (DisplayGalleryActivity) context;
        this.galleryModelList = galleryModelList;
        deleted = new ArrayList<>();
    }

    @Override
    public myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.galary_display_row,parent,false);

        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(myHolder holder, int position) {
        Bitmap bitmap = galleryModelList.get(position);
        holder.BindData(bitmap);
        if (activity.is_normal)
        {
            if (holder.checkBox.isChecked())
            {
                holder.checkBox.setChecked(false);
                holder.gallery_img.setAlpha(1f);
            }
        }
    }

    @Override
    public int getItemCount() {
        return galleryModelList.size();
    }

    public class myHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView gallery_img;
        CheckBox checkBox;
        public myHolder(View itemView) {
            super(itemView);
            gallery_img =itemView.findViewById(R.id.galary_img);
            checkBox = itemView.findViewById(R.id.checkbox);
            checkBox.setOnClickListener(this);
        }

        public void BindData(Bitmap galleryModel)
        {
            gallery_img.setImageBitmap(galleryModel);

        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.checkbox)
            {
                if (checkBox.isChecked())
                {
                    gallery_img.setAlpha(.2f);
                    deleted.add(galleryModelList.get(getAdapterPosition()));
                    activity.UpdateToolBar(deleted,getAdapterPosition());
                    Log.e("adaptersize",deleted.size()+"");

                }else
                    {
                        gallery_img.setAlpha(1f);
                        deleted.remove(galleryModelList.get(getAdapterPosition()));
                        activity.UpdateToolBar(deleted,getAdapterPosition());
                        Log.e("adaptersize2",deleted.size()+"");



                    }
            }
        }
    }


    public void UpdateAdapter(List<Bitmap> deleted)
    {
        galleryModelList.removeAll(deleted);
        notifyDataSetChanged();
    }
    public void AddData(List<Bitmap> addedd_bitmap)
    {
        galleryModelList.clear();
        galleryModelList.addAll(addedd_bitmap);
        notifyDataSetChanged();
    }
    public void clearList()
    {
        deleted.clear();
    }



}
