package com.openclassrooms.mddapi.payload.response;

/**
 * Represents a generic response payload used for providing messages.
 * This class encapsulates a message that can be used for various purposes such as
 * error messages, success notifications, or informational messages.
 */
public class GenericResponse {

    /**
     * The message to be conveyed in the response.
     */
    private String message;

    /**
     * Constructs a new GenericResponse with the specified message.
     * 
     * @param message the message to be included in the response
     */
    public GenericResponse(String message) {
        this.message = message;
    }

    /**
     * Gets the message from the response.
     * 
     * @return the message included in the response
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message for the response.
     * 
     * @param message the message to set in the response
     */
    public void setMessage(String message) {
        this.message = message;
    }
}