package com.Alatheer.Projects.laylaky.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Alatheer.Projects.laylaky.Activites.OfferAlbum;
import com.Alatheer.Projects.laylaky.Models.OfferModel;
import com.Alatheer.Projects.laylaky.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by m on 3/11/2018.
 */

public class AdapterAlbums extends RecyclerView.Adapter<AdapterAlbums.Holder> {

    OfferModel OfferModel;
    List<OfferModel> OfferModelList;
    Context context;
    OfferAlbum albumsActivity;

    public AdapterAlbums(List<OfferModel> OfferModelList, Context context) {
        this.OfferModelList = OfferModelList;
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

        OfferModel=OfferModelList.get(position);

       // holder.linear.setTag(position);

        holder.titlealbum.setText(OfferModel.getTitle());

        Picasso.with(context).load(OfferModel.getImg()).into(holder.imgalbum);

       // holder.imgalbum.setImageResource(OfferModel.get());

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
        return OfferModelList.size();
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
//            OfferModel=OfferModelList.get(postion);
//            i.putExtra("title",OfferModel.getTitle());
//            i.putExtra("detail",OfferModel.getDesc());
//            i.putExtra("price",OfferModel.getPrice());
//            i.putExtra("img",OfferModel.getImg());
//
//
//            context.startActivity(i);
//
//
//        }
    }
}
