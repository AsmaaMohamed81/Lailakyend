package com.Alatheer.Projects.lailaky.Models;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class typeimg {



    private List<Bitmap> bitmapList = new ArrayList<>();
    private int type;

    public typeimg(List<Bitmap> bitmapList, int type) {
        this.bitmapList = bitmapList;
        this.type = type;
    }

    public List<Bitmap> getBitmapList() {
        return bitmapList;
    }

    public void setBitmapList(List<Bitmap> bitmapList) {
        this.bitmapList = bitmapList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
