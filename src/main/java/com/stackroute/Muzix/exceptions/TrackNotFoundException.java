package com.stackroute.Muzix.exceptions;

//To handle track not found exception
public class TrackNotFoundException extends Exception {
    private String message;

    public TrackNotFoundException() {
    }

    public TrackNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
