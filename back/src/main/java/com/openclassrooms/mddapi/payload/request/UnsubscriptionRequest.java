package com.openclassrooms.mddapi.payload.request;
/**
 * DTO used to unsubscribe from a theme.
 * Carries the subscription ID.
 */

public class UnsubscriptionRequest {
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}