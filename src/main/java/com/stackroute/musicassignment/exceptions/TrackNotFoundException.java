package com.stackroute.musicassignment.exceptions;

public class TrackNotFoundException extends Exception {

    private String message;

    //custom exception for TrackNotFound
    public TrackNotFoundException(){}

    public TrackNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
