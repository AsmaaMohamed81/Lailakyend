package com.Alatheer.Projects.lailaky.Models;

public class ImgModel {

    private String image_id;
    private String image;
    private String type;

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public ImgModel() {
    }

    public ImgModel(String image) {

        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getType() {
        return type;
    }
}
