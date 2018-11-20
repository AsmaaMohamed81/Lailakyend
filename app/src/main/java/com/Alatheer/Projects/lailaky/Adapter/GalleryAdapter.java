package com.Alatheer.Projects.lailaky.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Alatheer.Projects.lailaky.Activites.DetailsAlbumaty;
import com.Alatheer.Projects.lailaky.ApiServices.Tags;
import com.Alatheer.Projects.lailaky.Models.ImgModel;
import com.Alatheer.Projects.lailaky.R;
import com.github.chrisbanes.photoview.PhotoView;
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
        ImgModel imgModel2 = imgModelList.get(position+1);
        holder.BindData(imgModel);
        holder.BindData(imgModel2);

       /* if (detailsAlbumaty.isContextMode)
        {
            holder.checkbox.setVisibility(View.VISIBLE);

        }else
            {
                holder.checkbox.setChecked(false);
                holder.checkbox.setVisibility(View.GONE);

            }
        holder.itemView.setOnLongClickListener(detailsAlbumaty);
        //holder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailsAlbumaty.SetPos(view,holder.getAdapterPosition());
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return imgModelList.size();
    }

    public class myHolder extends RecyclerView.ViewHolder
    {
        //CheckBox checkbox;
        public PhotoView gallery_Img1,gallery_Img2;
        public myHolder(View itemView) {
            super(itemView);
            gallery_Img1 = itemView.findViewById(R.id.galler_Img1);
            gallery_Img2 = itemView.findViewById(R.id.galler_Img2);

            //checkbox = itemView.findViewById(R.id.checkbox);

        }

        public void BindData(ImgModel imgModel)
        {
            target = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    gallery_Img1.setImageBitmap(resizeBitmap(bitmap));
                    gallery_Img2.setImageBitmap(resizeBitmap(bitmap));

                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            };

            Log.e("fsdfsdddddd",Tags.ImgPath+imgModel.getImage());
            Picasso.with(context).load(Uri.parse(Tags.ImgPath+imgModel.getImage())).into(target);
            Picasso.with(context).load(Uri.parse(Tags.ImgPath+imgModel.getImage())).into(target);

        }
    }

    private Bitmap resizeBitmap(Bitmap bitmap)
    {
        int maxWidth = 620;
        int maxHeight=1250;

        float scale = Math.min(((float)maxHeight / bitmap.getWidth()), ((float)maxWidth / bitmap.getHeight()));
        Matrix matrix = new Matrix();
        matrix.postScale(scale,scale);
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        return bitmap1;

}
    public void DeleteImages(List<ImgModel> imgModels)
    {
        imgModelList.removeAll(imgModels);
        notifyDataSetChanged();
    }
    public void AddImage(List<ImgModel> imgModels)
    {
        imgModelList.clear();
        imgModelList.addAll(imgModels);
        notifyDataSetChanged();
    }



}
