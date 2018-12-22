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

import com.Alatheer.Projects.lailaky.Activites.FinalAlbumActivity;
import com.Alatheer.Projects.lailaky.ApiServices.Tags;
import com.Alatheer.Projects.lailaky.Models.FinalImageModel;
import com.Alatheer.Projects.lailaky.R;

import java.util.List;

public class MyPagerAdapter extends RecyclerView.Adapter {

    private static final int ITEM_ONE_IMAGE=1;
    private static final int ITEM_TWO_IMAGE=2;

    private Context context;
    private List<FinalImageModel> finalImageModelList;
    private FinalAlbumActivity  activity;

    public MyPagerAdapter(Context context, List<FinalImageModel> finalImageModelList) {
        this.context = context;
        this.finalImageModelList = finalImageModelList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TWO_IMAGE)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.two_image_row,parent,false);
            return new HolderTowImage(view);
        }else
            {
                View view = LayoutInflater.from(context).inflate(R.layout.one_image_row,parent,false);
                return new HolderOneImage(view);
            }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HolderOneImage)
        {
            HolderOneImage holderOneImage = (HolderOneImage) holder;
            FinalImageModel finalImageModel = finalImageModelList.get(holderOneImage.getAdapterPosition());
            holderOneImage.BindData(finalImageModel);
        }else if (holder instanceof HolderTowImage)
        {
            HolderTowImage holderTowImage = (HolderTowImage) holder;
            FinalImageModel finalImageModel = finalImageModelList.get(holderTowImage.getAdapterPosition());
            holderTowImage.BindData(finalImageModel);
        }

    }

    @Override
    public int getItemCount() {
        return finalImageModelList.size();
    }


    public class HolderOneImage extends RecyclerView.ViewHolder {
        private ImageView image1;
        public HolderOneImage(View itemView) {
            super(itemView);
            image1 = itemView.findViewById(R.id.image1);

        }

        public void BindData(FinalImageModel finalImageModel)
        {
            Bitmap bitmap1  = BitmapFactory.decodeByteArray(finalImageModel.getImage1(),0,finalImageModel.getImage1().length);

            image1.setImageBitmap(bitmap1);

        }
    }

    public class HolderTowImage extends RecyclerView.ViewHolder {
        private ImageView image1,image2;

        public HolderTowImage(View itemView) {
            super(itemView);

            image1 = itemView.findViewById(R.id.image1);
            image2 = itemView.findViewById(R.id.image2);
        }

        public void BindData(FinalImageModel finalImageModel)
        {
            Bitmap bitmap1  = BitmapFactory.decodeByteArray(finalImageModel.getImage1(),0,finalImageModel.getImage1().length);

            image1.setImageBitmap(bitmap1);

            if (finalImageModel.getImage2()!=null && finalImageModel.getImage2().length>0)
            {
                Bitmap bitmap2  = BitmapFactory.decodeByteArray(finalImageModel.getImage2(),0,finalImageModel.getImage2().length);

                image2.setImageBitmap(bitmap2);

            }



        }
    }

    @Override
    public int getItemViewType(int position) {
        FinalImageModel finalImageModel = finalImageModelList.get(position);
        if (finalImageModel.getType().equals(Tags.type_two_pages))
        {
            return ITEM_TWO_IMAGE;
        }else
            {
                return ITEM_ONE_IMAGE;
            }
    }
}
