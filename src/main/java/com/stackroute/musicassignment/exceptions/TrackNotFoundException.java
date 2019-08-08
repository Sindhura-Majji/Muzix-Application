package com.stackroute.musicassignment.exceptions;

public class TrackNotFoundException extends Exception {

    private String message;

    //custom exception for TrackNotFoundException
    public TrackNotFoundException(){}

    public TrackNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
