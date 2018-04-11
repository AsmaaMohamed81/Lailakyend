package com.Alatheer.Projects.laylaky.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.Alatheer.Projects.laylaky.ApiServices.Tags;
import com.Alatheer.Projects.laylaky.Models.ImgModel;
import com.Alatheer.Projects.laylaky.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by Emad on 2018-04-11.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.myHolder>{

    private List<ImgModel> imgModelList;
    private Context context;
    private Target target;
    public GalleryAdapter(List<ImgModel> imgModelList, Context context) {
        this.imgModelList = imgModelList;
        this.context = context;
    }

    @Override
    public myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gallery_row,parent,false);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(myHolder holder, int position) {
        ImgModel imgModel = imgModelList.get(position);
        holder.BindData(imgModel);
    }

    @Override
    public int getItemCount() {
        return imgModelList.size();
    }

    public class myHolder extends RecyclerView.ViewHolder
    {
        private ImageView gallery_Img;
        public myHolder(View itemView) {
            super(itemView);
            gallery_Img = itemView.findViewById(R.id.galler_Img);
        }

        public void BindData(ImgModel imgModel)
        {
            target = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    gallery_Img.setImageBitmap(bitmap);
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            };

            Picasso.with(context).load(Uri.parse(Tags.ImgPath+imgModel.getImage())).into(target);
        }
    }
}
