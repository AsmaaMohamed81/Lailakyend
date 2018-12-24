package com.Alatheer.Projects.lailaky.SingleTone;

import android.graphics.Bitmap;
import android.util.Log;

import com.Alatheer.Projects.lailaky.Models.FinalImageModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by elashry on 23/07/2018.
 */

public class FinalAlbumImage {
    private static FinalAlbumImage instance;
   /* private List<Bitmap> bitmapList = new ArrayList<>();
    private List<String> imageTypeList = new ArrayList<>();*/
   private List<FinalImageModel> finalImageModelList = new ArrayList<>();
   private FinalImageModel finalImageModel_before_update,finalImageModel_after_update;
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

    public void addImage(FinalImageModel finalImageModel)
    {
        Log.e("type",finalImageModel.getType()+"_");
        finalImageModelList.add(finalImageModel);
    }

    public List<FinalImageModel> getImages()
    {
        return this.finalImageModelList;
    }

    public int getItemPositionInImages(byte [] image)
    {
        for (int i=0;i<finalImageModelList.size();i++)
        {
            if (Arrays.equals(image,finalImageModelList.get(i).getImage1()))
            {
                return i;
            }
        }

        return -1;
    }

    public void updateItemInImageList(FinalImageModel finalImageModel,int pos)
    {
        finalImageModelList.set(pos,finalImageModel);
    }


    public void ClearList()
    {
        if (this.finalImageModelList!=null)
        {
            this.finalImageModelList.clear();
            this.coverBitmap=null;
            this.finalImageModel_before_update=null;
            this.finalImageModel_after_update=null;
            ResetCount();
        }
    }

    public void setItemBeforeUpdate(FinalImageModel finalImageModel_before_update)
    {
        this.finalImageModel_before_update = finalImageModel_before_update;

    }

    public FinalImageModel getFinalImageModel_before_update() {
        return finalImageModel_before_update;
    }

    public void setFinalImageModel_after_update(FinalImageModel finalImageModel_after_update)
    {
        this.finalImageModel_after_update = finalImageModel_after_update;
    }

    public FinalImageModel getFinalImageModel_after_update() {
        return finalImageModel_after_update;
    }

    public void increaseCount(int value)
    {
        Log.e("value",value+"_");
        this.count+=value;
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


    public List<FinalImageModel> deleteItem(FinalImageModel finalImageModel, String type, String pos_for_delete)
    {
        for (int i =0;i <this.finalImageModelList.size();i++)
        {
            if (type.equals("1"))
            {
                if (pos_for_delete.equals("1"))
                {
                    if (Arrays.equals(finalImageModel.getImage1(),this.finalImageModelList.get(i).getImage1()))
                    {
                        increaseCount(-1);
                        this.finalImageModelList.remove(i);
                        break;
                    }
                }else if (pos_for_delete.equals("2"))
                {
                    if (Arrays.equals(finalImageModel.getImage2(),this.finalImageModelList.get(i).getImage1()))
                    {
                        increaseCount(-1);

                        this.finalImageModelList.remove(i);
                        break;
                    }
                }
            }else if (type.equals("2"))
            {
                if (Arrays.equals(finalImageModel.getImage1(),this.finalImageModelList.get(i).getImage1()))
                {
                    increaseCount(-2);

                    this.finalImageModelList.remove(i);
                    break;
                }
            }

        }

        return this.finalImageModelList;
    }
}
