package com.Alatheer.Projects.lailaky.Models;

public class PaperModel {

    private String Paper_type;
    private int img;
    private String Paper_id;

    public PaperModel(String paper_type, int img, String paper_id) {
        Paper_type = paper_type;
        this.img = img;
        Paper_id = paper_id;
    }

    public String getPaper_type() {
        return Paper_type;
    }

    public void setPaper_type(String paper_type) {
        Paper_type = paper_type;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getPaper_id() {
        return Paper_id;
    }

    public void setPaper_id(String paper_id) {
        Paper_id = paper_id;
    }
}
