package com.Alatheer.Projects.lailaky.Models;

import java.io.Serializable;

public class FinalImageModel implements Serializable {
    private byte [] image1;
    private byte [] image2;
    private String type;

    public FinalImageModel() {

    }


    public void setImage1(byte[] image1) {
        this.image1 = image1;
    }

    public void setImage2(byte[] image2) {
        this.image2 = image2;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImage1() {
        return image1;
    }

    public byte[] getImage2() {
        return image2;
    }

    public String getType() {
        return type;
    }
}
