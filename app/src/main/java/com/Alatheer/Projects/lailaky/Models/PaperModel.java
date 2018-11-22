package com.Alatheer.Projects.lailaky.Models;

public class PaperModel {

    private String paper_type;
    private String img;
    private String paper_id;

    public PaperModel(String paper_type, String img, String paper_id) {
        paper_type = paper_type;
        this.img = img;
        paper_id = paper_id;
    }

    public String getpaper_type() {
        return paper_type;
    }

    public void setpaper_type(String paper_type) {
        paper_type = paper_type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getpaper_id() {
        return paper_id;
    }

    public void setpaper_id(String paper_id) {
        paper_id = paper_id;
    }
}
