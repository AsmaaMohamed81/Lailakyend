package com.Alatheer.Projects.laylaky.SingleTone;

import android.graphics.Bitmap;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.POST;

/**
 * Created by elashry on 23/07/2018.
 */

public class FinalAlbumImage {
    private static FinalAlbumImage instance;
    private List<Bitmap> bitmapList = new ArrayList<>();
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
