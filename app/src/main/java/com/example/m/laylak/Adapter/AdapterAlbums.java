package com.example.m.laylak.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.m.laylak.Activites.AlbumsActivity;
import com.example.m.laylak.Activites.DetailOffer;
import com.example.m.laylak.Activites.OfferAlbum;
import com.example.m.laylak.Models.Modelalbums;
import com.example.m.laylak.R;

import java.util.List;

/**
 * Created by m on 3/11/2018.
 */

public class AdapterAlbums extends RecyclerView.Adapter<AdapterAlbums.Holder> {

    Modelalbums modelalbums;
    List<Modelalbums> modelalbumsList;
    Context context;
    OfferAlbum albumsActivity;

    public AdapterAlbums(List<Modelalbums> modelalbumsList, Context context) {
        this.modelalbumsList = modelalbumsList;
        this.context = context;
        albumsActivity= (OfferAlbum) context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemalbums,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {

        modelalbums=modelalbumsList.get(position);

       // holder.linear.setTag(position);

        holder.titlealbum.setText(modelalbums.getTitle());
        holder.imgalbum.setImageResource(modelalbums.getImg());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos= holder.getAdapterPosition();
                albumsActivity.pos(pos);

            }
        });


    }

    @Override
    public int getItemCount() {
        return modelalbumsList.size();
    }


    class Holder extends RecyclerView.ViewHolder  {
        ImageView imgalbum;
        TextView titlealbum;
        LinearLayout linear;
        public Holder(View itemView) {
            super(itemView);

            imgalbum=itemView.findViewById(R.id.imgalbum);
            titlealbum=itemView.findViewById(R.id.titlealbum);
            linear=itemView.findViewById(R.id.linesr);

//            linear.setOnClickListener(this);




        }

//        @Override
//        public void onClick(View view) {
//            Intent i = new Intent(context, DetailOffer.class);
//
//            int postion = (int) view.getTag();
//
//            modelalbums=modelalbumsList.get(postion);
//            i.putExtra("title",modelalbums.getTitle());
//            i.putExtra("detail",modelalbums.getDesc());
//            i.putExtra("price",modelalbums.getPrice());
//            i.putExtra("img",modelalbums.getImg());
//
//
//            context.startActivity(i);
//
//
//        }
    }
}
