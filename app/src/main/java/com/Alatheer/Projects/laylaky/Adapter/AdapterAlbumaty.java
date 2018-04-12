package com.Alatheer.Projects.laylaky.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Alatheer.Projects.laylaky.Activites.Albumaty;
import com.Alatheer.Projects.laylaky.Models.OfferModel;
import com.Alatheer.Projects.laylaky.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by m on 3/11/2018.
 */

public class AdapterAlbumaty extends RecyclerView.Adapter<AdapterAlbumaty.Holder> {

    OfferModel OfferModel;
    List<OfferModel> OfferModelList;
    Context context;
    Albumaty albumaty;


    public AdapterAlbumaty(List<OfferModel> OfferModelList, Context context) {
        this.OfferModelList = OfferModelList;
        //this.context = context;
        albumaty = (Albumaty) context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(albumaty).inflate(R.layout.itemalbumaty,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {

        OfferModel=OfferModelList.get(position);

        holder.linear.setTag(position);

        holder.titlealbum.setText(OfferModel.getTitle());

        Picasso.with(albumaty).load(OfferModel.getImg()).into(holder.imgalbum);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos= holder.getAdapterPosition();
                albumaty.pos(pos);

            }
        });

    }

    @Override
    public int getItemCount() {
        return OfferModelList.size();
    }


    class Holder extends RecyclerView.ViewHolder {
        ImageView imgalbum;
        TextView titlealbum;
        LinearLayout linear;
        public Holder(View itemView) {
            super(itemView);

            imgalbum=itemView.findViewById(R.id.imgalbum);
            titlealbum=itemView.findViewById(R.id.titlealbum);
            linear=itemView.findViewById(R.id.linesr);



        }


    }

}
