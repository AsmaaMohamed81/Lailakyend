package com.example.m.laylak.Models;

/**
 * Created by Emad on 2018-04-03.
 */

public class ResponsModel {
    private String user_id;
    private int success;
    private String message;

    public ResponsModel() {
    }

    public ResponsModel(String user_id, int success, String message) {
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

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
