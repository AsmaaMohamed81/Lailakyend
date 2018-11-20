package com.Alatheer.Projects.lailaky.Models;

import java.io.Serializable;

public class ModelContactUs implements Serializable {

    private String tele_info;
    private String email_info;
    private String web_info;
    private String time_work;
    private double location_google_lat;
    private double location_google_long;

    public String getTele_info() {
        return tele_info;
    }

    public String getEmail_info() {
        return email_info;
    }

    public String getWeb_info() {
        return web_info;
    }

    public double getLocation_google_lat() {
        return location_google_lat;
    }

    public double getLocation_google_long() {
        return location_google_long;
    }

    public String getTime_work() {
        return time_work;
    }
}
