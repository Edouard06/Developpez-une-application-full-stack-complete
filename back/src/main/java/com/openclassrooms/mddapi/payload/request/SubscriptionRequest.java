package com.openclassrooms.mddapi.payload.request;

import jakarta.validation.constraints.NotNull;
/**
 * DTO for creating a new subscription to a theme.
 * Contains the ID of the theme to subscribe to.
 */


public class SubscriptionRequest {

    @NotNull
    private Integer theme_id;

    public Integer getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(Integer theme_id) {
        this.theme_id = theme_id;
    }
}