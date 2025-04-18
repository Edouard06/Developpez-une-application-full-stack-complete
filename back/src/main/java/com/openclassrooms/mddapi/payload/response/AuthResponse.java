package com.openclassrooms.mddapi.payload.response;
/**
 * Response returned after successful authentication.
 * Contains the JWT token.
 */



public class AuthResponse {

   
    private String token;

    
    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}