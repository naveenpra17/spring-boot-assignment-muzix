package com.stackroute.trackservice.exceptions;

/**
 * user deifened exception class for user alreaddy exists
 */
public class UserAlreadyExistsException extends Exception {
    private String message;
            public UserAlreadyExistsException(){}

            public UserAlreadyExistsException(String message){
                    super(message);
                    this.message=message;
            }


}
