package com.example.news;

import com.google.gson.annotations.SerializedName;

public class ArticlesList {
    @SerializedName("urlToImage")
    String image_url;
    String title;
    SourceModel source;
    String url;
    String description;
    @SerializedName("publishedAt")
    String timimg;

    public ArticlesList(String image_url, String title, SourceModel source, String url, String description, String timimg) {
        this.image_url = image_url;
        this.title = title;
        this.source = source;
        this.url = url;
        this.description = description;
        this.timimg = timimg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimimg() {
        return timimg;
    }

    public void setTimimg(String timimg) {
        this.timimg = timimg;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SourceModel getSource() {
        return source;
    }

    public void setSource(SourceModel source) {
        this.source = source;
    }
}
