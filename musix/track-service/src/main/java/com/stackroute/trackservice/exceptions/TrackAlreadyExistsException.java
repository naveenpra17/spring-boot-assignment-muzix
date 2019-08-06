package com.stackroute.trackservice.exceptions;

/**
 * user deifened exception class for user alreaddy exists
 */
public class TrackAlreadyExistsException extends Exception {
    private String message;
            public TrackAlreadyExistsException(){}

            public TrackAlreadyExistsException(String message){
                    super(message);
                    this.message=message;
            }


}
