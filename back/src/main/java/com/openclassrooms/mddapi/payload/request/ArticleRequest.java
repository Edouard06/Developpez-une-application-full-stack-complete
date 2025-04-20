package com.openclassrooms.mddapi.payload.request;

import jakarta.validation.constraints.NotBlank;
/**
 * DTO for creating a new article.
 * Contains the theme ID, title and content.
 */
public class ArticleRequest {
    @NotBlank
    private String title;

    @NotBlank
    private  String content;

    @NotBlank
    private Integer theme_id;

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