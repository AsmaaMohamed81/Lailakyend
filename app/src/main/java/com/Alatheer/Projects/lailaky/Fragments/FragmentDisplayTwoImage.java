package com.Alatheer.Projects.lailaky.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Alatheer.Projects.lailaky.ApiServices.Tags;
import com.Alatheer.Projects.lailaky.Models.GalleryImagesModel;
import com.Alatheer.Projects.lailaky.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class FragmentDisplayTwoImage extends Fragment {
    private PhotoView image1,image2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_display_two_image,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        image1 = view.findViewById(R.id.image1);
        image2 = view.findViewById(R.id.image2);

        Bundle bundle = getArguments();
        if (bundle!=null)
        {
            GalleryImagesModel galleryImagesModel = (GalleryImagesModel) bundle.getSerializable("data");
            updateUi(galleryImagesModel);

        }
    }

    private void updateUi(GalleryImagesModel galleryImagesModel) {
        Picasso.with(getActivity()).load(Uri.parse(Tags.ImgPath+galleryImagesModel.getFirst_image_name())).into(image1);
        if (!TextUtils.isEmpty(galleryImagesModel.getSecond_image_name()))
        {
            Picasso.with(getActivity()).load(Uri.parse(Tags.ImgPath+galleryImagesModel.getSecond_image_name())).into(image2);

        }

    }

    public static FragmentDisplayTwoImage newInstance(GalleryImagesModel galleryImagesModel)
    {
        FragmentDisplayTwoImage fragmentDisplayTwoImage = new FragmentDisplayTwoImage();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",galleryImagesModel);
        fragmentDisplayTwoImage.setArguments(bundle);
        return fragmentDisplayTwoImage;
    }
}
