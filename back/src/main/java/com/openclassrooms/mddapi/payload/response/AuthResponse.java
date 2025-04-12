package com.openclassrooms.mddapi.payload.response;

/**
 * Represents the response payload for authentication operations.
 * This class encapsulates the authentication token provided upon successful login.
 */
public class AuthResponse {

    /**
     * The authentication token issued to the user.
     */
    private String token;

    /**
     * Constructs a new AuthResponse with the specified token.
     * 
     * @param token the authentication token
     */
    public AuthResponse(String token) {
        this.token = token;
    }

    /**
     * Gets the authentication token.
     * 
     * @return the authentication token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the authentication token.
     * 
     * @param token the authentication token to set
     */
    public void setToken(String token) {
        this.token = token;
    }
}