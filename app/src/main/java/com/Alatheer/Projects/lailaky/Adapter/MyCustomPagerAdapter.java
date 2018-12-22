package com.Alatheer.Projects.lailaky.Adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.Alatheer.Projects.lailaky.Fragments.Fragment_Image_Album_Item;

import java.util.ArrayList;
import java.util.List;

public class MyCustomPagerAdapter extends FragmentPagerAdapter {

    private List<byte[]> bitmapList;
    private List<Fragment> fragmentList;

    public MyCustomPagerAdapter(FragmentManager fm,List<byte[]> bitmapList) {
        super(fm);
        this.bitmapList = bitmapList;
        fragmentList = new ArrayList<>();
        for (byte[] bytes : bitmapList)
        {
            fragmentList.add(Fragment_Image_Album_Item.getInstance(bytes));
        }




    }




    @Override
    public int getCount() {
        return bitmapList.size();
    }

    @Override
    public float getPageWidth(int position) {
        return .85f;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment_Image_Album_Item fragment_image_album_item = (Fragment_Image_Album_Item) fragmentList.get(position);
        fragment_image_album_item.setPos(position);
        return fragment_image_album_item;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }


}
