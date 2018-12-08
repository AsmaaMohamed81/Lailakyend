package com.Alatheer.Projects.lailaky.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.Alatheer.Projects.lailaky.Models.AboutUsModel;
import com.Alatheer.Projects.lailaky.R;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;


public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.Holder> {

    AboutUsModel modelabout;
    List<AboutUsModel> aboutUsModels;
    Context context;

    private static final int UNSELECTED = -1;

    private RecyclerView recyclerView;
    private int selectedItem = UNSELECTED;

    public AboutAdapter(List<AboutUsModel> aboutUsModels, Context context,RecyclerView recyclerView) {
        this.aboutUsModels = aboutUsModels;
        this.context=context;
        this.recyclerView = recyclerView;


    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.about_item,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {

        holder.bind();




    }

    @Override
    public int getItemCount() {
        return aboutUsModels.size();
    }


    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener, ExpandableLayout.OnExpansionUpdateListener {
        TextView title,content;
        ExpandableLayout expand_layout;

        Holder(View itemView) {
            super(itemView);

             title=itemView.findViewById(R.id.txt_title);
             content=itemView.findViewById(R.id.txt_content);
             expand_layout=itemView.findViewById(R.id.expand_layout);

            expand_layout.setInterpolator(new OvershootInterpolator());
            expand_layout.setOnExpansionUpdateListener(this);

            title.setOnClickListener(this);




         }




        public void bind() {



            int position = getAdapterPosition();
            modelabout=aboutUsModels.get(position);
            boolean isSelected = position == selectedItem;
            title.setText(modelabout.getTitle());
            content.setText(modelabout.getContent());
            title.setSelected(isSelected);
            expand_layout.setExpanded(isSelected, false);


        }


        @Override
        public void onClick(View v) {

            Holder holder = (Holder) recyclerView.findViewHolderForAdapterPosition(selectedItem);
            if (holder != null) {
                holder.title.setSelected(false);
                holder.expand_layout.collapse();
            }

            int position = getAdapterPosition();
            if (position == selectedItem) {
                selectedItem = UNSELECTED;
            } else {
                title.setSelected(true);
                expand_layout.expand();
                selectedItem = position;
            }
        }

        @Override
        public void onExpansionUpdate(float expansionFraction, int state) {

            Log.d("ExpandableLayout", "State: " + state);
            if (state == ExpandableLayout.State.EXPANDING) {
                recyclerView.smoothScrollToPosition(getAdapterPosition());
            }
        }
    }
}
