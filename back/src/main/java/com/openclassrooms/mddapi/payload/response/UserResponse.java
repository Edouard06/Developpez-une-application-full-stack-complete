package com.openclassrooms.mddapi.payload.response;

import java.time.LocalDateTime;

/**
 * Represents a response payload for user details.
 * This class encapsulates information about a user, including
 * their identifiers, username, email, and timestamps for creation
 * and last update.
 */
public class UserResponse {

    /**
     * The unique identifier of the user.
     */
    private Integer id; 

    /**
     * The username of the user.
     */
    private String username; 

    /**
     * The email address of the user.
     */
    private String email; 

    /**
     * The timestamp when the user was created.
     */
    private LocalDateTime createdAt;

    /**
     * The timestamp when the user was last updated.
     */
    private LocalDateTime updatedAt;

    /**
     * Constructs a new {@code UserResponse} with the specified details.
     * 
     * @param id the unique identifier of the user
     * @param username the username of the user
     * @param email the email address of the user
     * @param createdAt the timestamp when the user was created
     * @param updatedAt the timestamp when the user was last updated
     */
    public UserResponse(Integer id, String username, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Gets the unique identifier of the user.
     * 
     * @return the unique identifier of the user
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     * 
     * @param id the unique identifier to set for the user
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the username of the user.
     * 
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     * 
     * @param username the username to set for the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the email address of the user.
     * 
     * @return the email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     * 
     * @param email the email address to set for the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the timestamp when the user was created.
     * 
     * @return the creation timestamp of the user
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when the user was created.
     * 
     * @param createdAt the creation timestamp to set for the user
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the timestamp when the user was last updated.
     * 
     * @return the last update timestamp of the user
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp when the user was last updated.
     * 
     * @param updatedAt the last update timestamp to set for the user
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}