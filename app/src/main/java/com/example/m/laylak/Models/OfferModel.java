package com.example.m.laylak.Models;

import com.example.m.laylak.ApiServices.Tags;

/**
 * Created by m on 4/4/2018.
 */

public class OfferModel {

   private String offer_id;
   private String title;
   private String img;
   private String details;
   private String price;

    public OfferModel(String title, String img, String details, String price) {
        this.title = title;
        this.img = img;
        this.details = details;
        this.price = price;
    }

    public void setOffer_id(String offer_id) {

        this.offer_id = offer_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOffer_id() {

        return offer_id;
    }

    public String getTitle() {
        return title;
    }

    public String getImg() {
        return Tags.ImgPath+img;
    }

    public String getDetails() {
        return details;
    }

    public String getPrice() {
        return price;
    }
}
