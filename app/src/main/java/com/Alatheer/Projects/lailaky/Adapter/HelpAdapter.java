package com.Alatheer.Projects.lailaky.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Alatheer.Projects.lailaky.Models.HelpModel;
import com.Alatheer.Projects.lailaky.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.Holder> {

    private ArrayList<HelpModel> list;
    private Context context;


    public HelpAdapter(ArrayList<HelpModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_help,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.Bind(list.get(position));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        ImageView image;
        public Holder(View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.img_item);

        }

        public void  Bind (HelpModel HelpModel){


            Picasso.with(context).load(HelpModel.getImg()).into(image);


        }
    }
}
