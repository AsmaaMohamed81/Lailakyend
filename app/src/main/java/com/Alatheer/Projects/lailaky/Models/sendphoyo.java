package com.Alatheer.Projects.lailaky.Models;

import java.util.List;

import okhttp3.MultipartBody;

public class sendphoyo {
    MultipartBody.Part part;
    typeimg typeimg;

    public sendphoyo(MultipartBody.Part part,typeimg typeimg) {
        this.part = part;
        this.typeimg = typeimg;
    }

    public MultipartBody.Part getPart() {
        return part;
    }

    public void setPart(MultipartBody.Part part) {
        this.part = part;
    }

    public int getTypeimg() {
        return typeimg.getType();
    }

    public void setTypeimg(com.Alatheer.Projects.lailaky.Models.typeimg typeimg) {
        this.typeimg = typeimg;
    }
}
