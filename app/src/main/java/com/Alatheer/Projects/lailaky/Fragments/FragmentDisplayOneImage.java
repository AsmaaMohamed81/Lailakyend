package com.Alatheer.Projects.lailaky.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.Alatheer.Projects.lailaky.ApiServices.Tags;
import com.Alatheer.Projects.lailaky.Models.GalleryImagesModel;
import com.Alatheer.Projects.lailaky.R;
import com.squareup.picasso.Picasso;

public class FragmentDisplayOneImage extends Fragment {
    private ImageView image1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_display_one_image,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        image1 = view.findViewById(R.id.image1);
        Bundle bundle = getArguments();
        if (bundle!=null)
        {
            GalleryImagesModel galleryImagesModel = (GalleryImagesModel) bundle.getSerializable("data");
            updateUi(galleryImagesModel);

        }
    }

    private void updateUi(GalleryImagesModel galleryImagesModel) {
        Picasso.with(getActivity()).load(Uri.parse(Tags.ImgPath+galleryImagesModel.getFirst_image_name())).into(image1);
    }

    public static FragmentDisplayOneImage newInstance(GalleryImagesModel galleryImagesModel)
    {
        FragmentDisplayOneImage fragmentDisplayOneImage = new FragmentDisplayOneImage();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",galleryImagesModel);
        fragmentDisplayOneImage.setArguments(bundle);
        return fragmentDisplayOneImage;
    }
}
