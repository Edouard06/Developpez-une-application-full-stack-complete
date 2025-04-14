package com.openclassrooms.mddapi.payload.response;
/**
 * GenericResponse is a simple class that represents a generic response message.
 * It contains a single field 'message' which can be used to convey any information
 * back to the client.
 */
public class GenericResponse {

    
    private String message;

   
    public GenericResponse(String message) {
        this.message = message;
    }

   
    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }
}