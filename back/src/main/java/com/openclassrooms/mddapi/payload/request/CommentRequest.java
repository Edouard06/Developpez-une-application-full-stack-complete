package com.openclassrooms.mddapi.payload.request;

import jakarta.validation.constraints.NotBlank;
/**
 * DTO used to post a new comment on an article.
 * Carries the content and the article ID.
 */

public class CommentRequest {

    @NotBlank
    private String content;

    @NotBlank
    private Integer article_id;

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}