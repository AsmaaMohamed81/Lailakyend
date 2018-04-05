package com.Alatheer.Projects.laylaky.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.Alatheer.Projects.laylaky.Models.ImgModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.Alatheer.Projects.laylaky.ApiServices.Tags.ImgPath;

public class CustomGalleryAdapter extends BaseAdapter {

    private Context context;
    private List<ImgModel> images;

    ImgModel imgModel;

    public CustomGalleryAdapter(Context c, List<ImgModel> images) {
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
        imgModel=images.get(position);

        // create a ImageView programmatically
        ImageView imageView = new ImageView(context);
     //   imageView.setImageURI(images.get(position));
        Picasso.with(context).load(ImgPath+imgModel.getImage()).into(imageView);
      //  Toast.makeText(context, ""+ImgPath+imgModel.getImage(), Toast.LENGTH_SHORT).show();
        //imageView.setImageResource(images[position]); // set image in ImageView
        imageView.setLayoutParams(new Gallery.LayoutParams(200, 200)); // set ImageView param
        return imageView;
    }
}