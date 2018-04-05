package com.Alatheer.Projects.laylaky.Models;

/**
 * Created by elashry on 4/3/2018.
 */

public class AboutUsModel {

    private String title;
    private String content;

    public AboutUsModel(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
