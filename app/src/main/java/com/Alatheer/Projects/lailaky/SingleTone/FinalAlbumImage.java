package com.Alatheer.Projects.lailaky.SingleTone;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elashry on 23/07/2018.
 */

public class FinalAlbumImage {
    private static FinalAlbumImage instance;
    private List<Bitmap> bitmapList = new ArrayList<>();
    private List<String> imageTypeList = new ArrayList<>();
    private int count=0;
    private  Bitmap coverBitmap=null;


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

    public List<Bitmap> getBitmaps()
    {
        return bitmapList;
    }

    public void ClearList()
    {
        if (bitmapList!=null)
        {
            this.imageTypeList.clear();
            bitmapList.clear();
            this.coverBitmap=null;
            ResetCount();
        }
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

    public void setCoverImage(Bitmap coverImage)
    {
        this.coverBitmap = coverImage;
    }

    public Bitmap getCoverImage()
    {
        return this.coverBitmap;
    }

    public void setCount(int count)
    {
        this.count=count;
    }

    public void addImageType(String type)
    {
        this.imageTypeList.add(type);
    }

    public List<String> getImageTypeList() {
        return imageTypeList;
    }

    public void deleteItem(int pos)
    {

        this.bitmapList.remove(pos);
        this.imageTypeList.remove(pos);
    }
}
