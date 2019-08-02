package com.stackroute.trackservice.exceptions;

/**
 * user defined class for track not available
 */
public class TrackNotAvailable extends Exception {
    private String message;

    public TrackNotAvailable (String message){
        super(message);
    }
}
