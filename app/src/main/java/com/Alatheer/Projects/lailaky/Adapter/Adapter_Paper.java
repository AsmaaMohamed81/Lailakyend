package com.Alatheer.Projects.lailaky.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.Alatheer.Projects.lailaky.Activites.TypeePaperActivity;
import com.Alatheer.Projects.lailaky.ApiServices.Tags;
import com.Alatheer.Projects.lailaky.Models.PaperModel;
import com.Alatheer.Projects.lailaky.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_Paper extends RecyclerView.Adapter<Adapter_Paper.ViewHolder>{

    private List<PaperModel> Paperlist;
     TypeePaperActivity mTypeePaperActivity;
     Context context;

    private int lastSelectedPosition = -1;
    public  String Paper_id="";

    public Adapter_Paper(List<PaperModel> brands, Context context) {

        Paperlist = brands;
        this.context=context;
        mTypeePaperActivity =(TypeePaperActivity) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_paper, parent, false);

        Adapter_Paper.ViewHolder viewHolder =
                new Adapter_Paper.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        PaperModel paperModel = Paperlist.get(position);
        holder.paper_type.setText(paperModel.getpaper_type());
        holder.paper_id.setText(paperModel.getpaper_id());


        Picasso.with(context).load(Uri.parse(Tags.ImgPath+paperModel.getImg())).into(holder.paperImage);


        //since only one radio button is allowed to be selected,
        // this condition un-checks previous selections
        holder.selectionState.setChecked(lastSelectedPosition == position);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos= holder.getAdapterPosition();
                mTypeePaperActivity.pos(pos);

            }
        });


    }

    @Override
    public int getItemCount() {
        return Paperlist.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {

        public TextView paper_type,paper_id;
        public ImageView paperImage;
        public RadioButton selectionState;

        public ViewHolder(View view) {
            super(view);
            paper_type = view.findViewById(R.id.paper_type);
            paperImage = view.findViewById(R.id.imgPaper);
            paper_id = view.findViewById(R.id.paper_id);

            selectionState = (RadioButton) view.findViewById(R.id.paper_select);

            selectionState.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();

                    Paper_id=paper_id.getText()+"";

//                    Toast.makeText(Adapter_Paper.this.context,
//                            "selected offer is " + Paper_id,
//                            Toast.LENGTH_LONG).show();

                    notifyDataSetChanged();


                }});}}

}
