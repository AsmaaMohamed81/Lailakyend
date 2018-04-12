package com.Alatheer.Projects.laylaky.Models;

public class ModelContactUs {

    private String tele_info;
    private String email_info;
    private String web_info;
    private double location_google_lat;
    private double location_google_long;

    public String getTele_info() {
        return tele_info;
    }

    public void setTele_info(String tele_info) {
        this.tele_info = tele_info;
    }

    public String getEmail_info() {
        return email_info;
    }

    public void setEmail_info(String email_info) {
        this.email_info = email_info;
    }

    public String getWeb_info() {
        return web_info;
    }

    public void setWeb_info(String web_info) {
        this.web_info = web_info;
    }

    public double getLocation_google_lat() {
        return location_google_lat;
    }

    public void setLocation_google_lat(double location_google_lat) {
        this.location_google_lat = location_google_lat;
    }

    public double getLocation_google_long() {
        return location_google_long;
    }

    public void setLocation_google_long(double location_google_long) {
        this.location_google_long = location_google_long;
    }

    public ModelContactUs(String tele_info, String email_info, String web_info, double location_google_lat, double location_google_long) {

        this.tele_info = tele_info;
        this.email_info = email_info;
        this.web_info = web_info;
        this.location_google_lat = location_google_lat;
        this.location_google_long = location_google_long;
    }
}
