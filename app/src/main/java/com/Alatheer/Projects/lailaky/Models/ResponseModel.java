package com.Alatheer.Projects.lailaky.Models;

import java.io.Serializable;

/**
 * Created by Emad on 2018-04-05.
 */

public class ResponseModel implements Serializable {

    private int success;
    private String message;
    private int success_upload;
    public ResponseModel() {
    }

    public ResponseModel(int success, String message) {
        this.success = success;
        this.message = message;
    }

    public int getSuccess_upload() {
        return success_upload;
    }

    public void setSuccess_upload(int success_upload) {
        this.success_upload = success_upload;
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
