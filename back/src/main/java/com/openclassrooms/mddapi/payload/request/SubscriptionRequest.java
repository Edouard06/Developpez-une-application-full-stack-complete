package com.openclassrooms.mddapi.payload.request;

import jakarta.validation.constraints.NotNull;

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