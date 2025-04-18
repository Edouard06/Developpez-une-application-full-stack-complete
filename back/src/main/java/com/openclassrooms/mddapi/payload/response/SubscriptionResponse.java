package com.openclassrooms.mddapi.payload.response;
/**
 * Response containing subscription details.
 * Includes subscription ID, theme ID, user ID and creation date.
 */


import java.time.LocalDateTime;

public class SubscriptionResponse {

    private Integer id;
    private Integer theme_id;
    private Integer user_id;
    private LocalDateTime createdAt;

    public Integer getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(Integer theme_id) {
        this.theme_id = theme_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}