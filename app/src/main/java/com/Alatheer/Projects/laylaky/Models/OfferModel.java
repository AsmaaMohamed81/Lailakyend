package com.Alatheer.Projects.laylaky.Models;

import com.Alatheer.Projects.laylaky.ApiServices.Tags;

/**
 * Created by m on 4/4/2018.
 */

public class OfferModel {

   private String offer_id;
   private String title;
   private String img;
   private String details;
   private String price;
   private String size_offer;
    private String album_id;



    public OfferModel(String offer_id, String title, String img, String details, String price, String size_offer, String album_id) {
        this.offer_id = offer_id;
        this.title = title;
        this.img = img;
        this.details = details;
        this.price = price;
        this.size_offer = size_offer;
        this.album_id = album_id;
    }

    public OfferModel() {

    }

    public void setSize_offer(String size_offer) {

        this.size_offer = size_offer;
    }

    public String getSize_offer() {

        return size_offer;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public String getAlbum_id() {

        return album_id;
    }


    public OfferModel(String offer_id, String title, String img, String details, String price, String album_id) {
        this.offer_id = offer_id;
        this.title = title;
        this.img = img;
        this.details = details;
        this.price = price;
        this.album_id = album_id;
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
