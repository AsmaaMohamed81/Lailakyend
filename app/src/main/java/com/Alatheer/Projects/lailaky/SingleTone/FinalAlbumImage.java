package com.Alatheer.Projects.lailaky.SingleTone;

import android.graphics.Bitmap;

import com.Alatheer.Projects.lailaky.Models.typeimg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elashry on 23/07/2018.
 */

public class FinalAlbumImage {
    private static FinalAlbumImage instance;
    private List<Bitmap> bitmapList = new ArrayList<>();
    private List<typeimg> typeList = new ArrayList<>();
    private int count=0;

    private FinalAlbumImage() {
    }

    public  static synchronized FinalAlbumImage getInstance()
    {

        if (instance==null)
        {
            instance = new FinalAlbumImage();
        }
        return instance;
    }

    public void setImages(List<Bitmap> bitmapList)
    {
        this.bitmapList.addAll(bitmapList);
    }

    public List<Bitmap> getbitmaps()
    {
        return bitmapList;
    }

    public void ClearList()
    {
        if (bitmapList!=null)
        {
            bitmapList.clear();
            ResetCount();
        }
    }

    public List<typeimg> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<typeimg> typeList) {
        this.typeList = typeList;
    }

    public void increaseCount()
    {
        this.count++;
    }

    public int getCount()
    {
        return count;
    }

    private void ResetCount()
    {
        this.count=0;
    }

    public void setCount(int count)
    {
        this.count=count;
    }
    public void deleteItem(List<Bitmap> bitmapList)
    {
        this.bitmapList.removeAll(bitmapList);
    }
}
