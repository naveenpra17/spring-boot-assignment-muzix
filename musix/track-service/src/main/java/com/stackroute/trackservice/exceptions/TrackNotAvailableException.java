
package com.stackroute.trackservice.exceptions;

/**
 * user defined class for track not available
 */
public class TrackNotAvailableException extends Exception {
    private String message;

    public TrackNotAvailableException (String message){
        super(message);
    }
}
