package com.example.m.laylak.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import java.util.List;

public class CustomGalleryAdapter extends BaseAdapter {

    private Context context;
    private List<Uri> images;

    public CustomGalleryAdapter(Context c, List<Uri> images) {
        context = c;
        this.images = images;
    }

    // returns the number of images
    public int getCount() {
        return images.size();
    }

    // returns the ID of an item
    public Object getItem(int position) {
        return position;
    }

    // returns the ID of an item
    public long getItemId(int position) {
        return position;
    }

    // returns an ImageView view
    public View getView(int position, View convertView, ViewGroup parent) {

        // create a ImageView programmatically
        ImageView imageView = new ImageView(context);
        imageView.setImageURI(images.get(position));
        //imageView.setImageResource(images[position]); // set image in ImageView
        imageView.setLayoutParams(new Gallery.LayoutParams(200, 200)); // set ImageView param
        return imageView;
    }
}