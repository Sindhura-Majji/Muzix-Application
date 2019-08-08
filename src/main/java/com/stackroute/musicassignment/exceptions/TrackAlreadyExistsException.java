package com.stackroute.musicassignment.exceptions;

public class TrackAlreadyExistsException extends Exception {

    private String message;

    //custom exception for TrackAlreadyExists
    public TrackAlreadyExistsException(){}

    public TrackAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }

}
