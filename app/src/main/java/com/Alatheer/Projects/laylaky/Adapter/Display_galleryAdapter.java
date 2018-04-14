package com.Alatheer.Projects.laylaky.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Alatheer.Projects.laylaky.R;

import java.util.List;

/**
 * Created by Emad on 2018-04-14.
 */

public class Display_galleryAdapter extends RecyclerView.Adapter<Display_galleryAdapter.myHolder> {

    private Context context;
    private List<Bitmap> bitmapList;

    public Display_galleryAdapter(Context context, List<Bitmap> bitmapList) {
        this.context = context;
        this.bitmapList = bitmapList;
    }

    @Override
    public myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.galary_display_row,parent,false);

        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(myHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return bitmapList.size();
    }

    public class myHolder extends RecyclerView.ViewHolder{
        public myHolder(View itemView) {
            super(itemView);
        }
    }
}
