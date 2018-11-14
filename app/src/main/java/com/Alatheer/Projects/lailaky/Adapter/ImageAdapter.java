package com.Alatheer.Projects.lailaky.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.Alatheer.Projects.lailaky.R;

public class ImageAdapter extends PagerAdapter {

    Context context;
    private int[] GalImages = new int[] {
            R.drawable.guid10,
            R.drawable.guid9,
            R.drawable.guid8,
            R.drawable.guid7,
            R.drawable.guid6,
            R.drawable.guid5,
            R.drawable.guid4,
            R.drawable.guid3,
            R.drawable.guid2,
            R.drawable.guid1

    };

    LayoutInflater mLayoutInflater;

    public ImageAdapter(Context context){
        this.context=context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return GalImages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.item_help, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.img_item);
        imageView.setImageResource(GalImages[position]);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}