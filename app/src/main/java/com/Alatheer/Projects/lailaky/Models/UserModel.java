package com.Alatheer.Projects.lailaky.Models;

/**
 * Created by Emad on 2018-04-03.
 */

public class UserModel {
    private String user_id;
    private String user_name;
    private String user_pass;
    private String user_email;
    private String user_phone;
    private String address;
    private String governate;
    private String city;
    private String token_id;
    private int success;
    private String message;

    public UserModel() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGovernate() {
        return governate;
    }

    public void setGovernate(String governate) {
        this.governate = governate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public UserModel(String user_id, String user_name, String user_pass, String user_email, String user_phone, String token_id, int success, String message) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_pass = user_pass;
        this.user_email = user_email;
        this.user_phone = user_phone;
        this.token_id = token_id;
        this.success = success;
        this.message = message;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getToken_id() {
        return token_id;
    }

    public void setToken_id(String token_id) {
        this.token_id = token_id;
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
