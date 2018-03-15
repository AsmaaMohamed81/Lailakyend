package com.example.m.laylak.Models;

/**
 * Created by m on 3/11/2018.
 */

public class Modelalbumaty {

    String title;
    int img;
    String desc;
    String price;


    public void setTitle(String title) {
        this.title = title;
    }

    public Modelalbumaty(String title, int img, String desc, String price) {
        this.title = title;
        this.img = img;

        this.desc = desc;
        this.price = price;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {

        return title;
    }

    public int getImg() {
        return img;
    }

    public String getDesc() {
        return desc;
    }

    public Modelalbumaty(String title, int img) {
        this.title = title;
        this.img = img;


    }
}
