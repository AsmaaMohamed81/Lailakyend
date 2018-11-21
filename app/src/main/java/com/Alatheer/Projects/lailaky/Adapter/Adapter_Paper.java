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
import android.widget.Toast;

import com.Alatheer.Projects.lailaky.Activites.TypeePaperActivity;
import com.Alatheer.Projects.lailaky.ApiServices.Tags;
import com.Alatheer.Projects.lailaky.Models.PaperModel;
import com.Alatheer.Projects.lailaky.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_Paper extends RecyclerView.Adapter<Adapter_Paper.ViewHolder>{

    private List<PaperModel> Paperlist;
    private Context context;

    private int lastSelectedPosition = -1;

    public Adapter_Paper(List<PaperModel> brands, TypeePaperActivity typeePaperActivity) {

        Paperlist = brands;
        context = typeePaperActivity;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PaperModel paperModel = Paperlist.get(position);
        holder.paper_type.setText(paperModel.getPaper_type());

        Picasso.with(context).load(Uri.parse(Tags.ImgPath+paperModel.getImg())).into(holder.paperImage);


        //since only one radio button is allowed to be selected,
        // this condition un-checks previous selections
        holder.selectionState.setChecked(lastSelectedPosition == position);

    }

    @Override
    public int getItemCount() {
        return Paperlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView paper_type;
        public ImageView paperImage;
        public RadioButton selectionState;

        public ViewHolder(View view) {
            super(view);
            paper_type = view.findViewById(R.id.paper_type);
            paperImage = view.findViewById(R.id.imgPaper);
            selectionState = (RadioButton) view.findViewById(R.id.paper_select);

            selectionState.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();

                    Toast.makeText(Adapter_Paper.this.context,
                            "selected offer is " + paper_type.getText(),
                            Toast.LENGTH_LONG).show();
                }});}}

}
