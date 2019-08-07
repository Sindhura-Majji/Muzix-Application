package com.stackroute.Muzix.exceptions;

//Exception to handle track already exists case
public class TrackAlreadyExistsException extends Exception{
    private String message;


    public TrackAlreadyExistsException() { }

    public TrackAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
