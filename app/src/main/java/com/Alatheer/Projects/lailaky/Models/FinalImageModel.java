package com.Alatheer.Projects.lailaky.Models;

import java.io.Serializable;

public class FinalImageModel implements Serializable {
    private byte [] image1;
    private byte [] image2;
    private String type;
    private String frame_type;
    private int position_on_frame;

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

    public String getFrame_type() {
        return frame_type;
    }

    public void setFrame_type(String frame_type) {
        this.frame_type = frame_type;
    }

    public int getPosition_on_frame() {
        return position_on_frame;
    }

    public void setPosition_on_frame(int position_on_frame) {
        this.position_on_frame = position_on_frame;
    }
}
