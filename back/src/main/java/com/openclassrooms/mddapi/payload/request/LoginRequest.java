package com.openclassrooms.mddapi.payload.request;

import jakarta.validation.constraints.NotBlank;

/**
 * Represents a request payload for user authentication.
 * This class contains the credentials required to log in a user.
 */
public class LoginRequest {

    /**
     * The email address of the user.
     * This field cannot be blank.
     */
    @NotBlank
    private String email;

    /**
     * The password of the user.
     * This field cannot be blank.
     */
    @NotBlank
    private String password;

    /**
     * Gets the email address of the user.
     * 
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     * 
     * @param email the email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password of the user.
     * 
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * 
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}