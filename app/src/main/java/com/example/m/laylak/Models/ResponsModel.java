package com.example.m.laylak.Models;

/**
 * Created by Emad on 2018-04-03.
 */

public class ResponsModel {
    private String user_id;
    private String success;
    private String message;

    public ResponsModel() {
    }

    public ResponsModel(String user_id, String success, String message) {
        this.user_id = user_id;
        this.success = success;
        this.message = message;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
