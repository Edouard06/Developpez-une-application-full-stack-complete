package com.openclassrooms.mddapi.payload.request;

import jakarta.validation.constraints.NotBlank;

public class ArticleRequest {
    @NotBlank
    String title;

    @NotBlank
    String content;

    @NotBlank
    Integer theme_id;

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

    public Integer getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(Integer theme_id) {
        this.theme_id = theme_id;
    }

    

}