package com.openclassrooms.mddapi.payload.request;

import jakarta.validation.constraints.NotBlank;

/**
 * Represents a request payload for user registration.
 * This class contains the necessary information to register a new user.
 */
public class RegisterRequest {

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
     * The username of the user.
     * This field cannot be blank.
     */
    @NotBlank
    private String username;

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

    /**
     * Gets the username of the user.
     * 
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     * 
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}