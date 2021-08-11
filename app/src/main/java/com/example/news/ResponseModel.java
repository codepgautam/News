package com.example.news;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseModel {
    @SerializedName("articles")
    ArrayList<ArticlesList> articles_lists = null;

    public ArrayList<ArticlesList> getArticles_lists() {
        return articles_lists;
    }

    public void setArticles_lists(ArrayList<ArticlesList> articles_lists) {
        this.articles_lists = articles_lists;
    }
}
