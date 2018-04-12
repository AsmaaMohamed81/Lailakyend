package com.Alatheer.Projects.laylaky.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.Alatheer.Projects.laylaky.Activites.DetailsAlbumaty;
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
    private DetailsAlbumaty detailsAlbumaty;
    public GalleryAdapter(List<ImgModel> imgModelList, Context context) {
        this.imgModelList = imgModelList;
        this.context = context;
        detailsAlbumaty = (DetailsAlbumaty) context;
    }

    @Override
    public myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gallery_row,parent,false);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(final myHolder holder, int position) {
        ImgModel imgModel = imgModelList.get(position);
        holder.BindData(imgModel);
        if (detailsAlbumaty.isContextMode)
        {
            holder.checkbox.setVisibility(View.VISIBLE);
            holder.bg.setVisibility(View.VISIBLE);

        }else
            {
                holder.checkbox.setChecked(false);
                holder.checkbox.setVisibility(View.GONE);
                holder.bg.setVisibility(View.GONE);

            }
        holder.itemView.setOnLongClickListener(detailsAlbumaty);
        holder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailsAlbumaty.SetPos(view,holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return imgModelList.size();
    }

    public class myHolder extends RecyclerView.ViewHolder
    {
        CheckBox checkbox;
        FrameLayout bg;
        private ImageView gallery_Img;
        public myHolder(View itemView) {
            super(itemView);
            gallery_Img = itemView.findViewById(R.id.galler_Img);
            checkbox = itemView.findViewById(R.id.checkbox);
            bg = itemView.findViewById(R.id.bg);
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
    public void DeleteImages(List<ImgModel> imgModels)
    {
        imgModelList.removeAll(imgModels);
        notifyDataSetChanged();
    }
}
