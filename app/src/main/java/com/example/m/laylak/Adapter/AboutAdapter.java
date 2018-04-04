package com.example.m.laylak.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.m.laylak.Models.AboutUsModel;
import com.example.m.laylak.R;

import java.util.List;


public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.Holder> {

    AboutUsModel modelabout;
    List<AboutUsModel> aboutUsModels;
    Context context;

    public AboutAdapter(List<AboutUsModel> aboutUsModels, Context context) {
        this.aboutUsModels = aboutUsModels;
        this.context=context;
      
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.about_item,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {

        modelabout=aboutUsModels.get(position);


        holder.title.setText(modelabout.getTitle());
        holder.content.setText(modelabout.getContent());



    }

    @Override
    public int getItemCount() {
        return aboutUsModels.size();
    }


    class Holder extends RecyclerView.ViewHolder {
        TextView title,content;
         Holder(View itemView) {
            super(itemView);

             title=itemView.findViewById(R.id.txt_title);
             content=itemView.findViewById(R.id.txt_content);



        }

      
    }
}
