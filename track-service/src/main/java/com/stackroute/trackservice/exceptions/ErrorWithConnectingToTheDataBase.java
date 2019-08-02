package com.stackroute.trackservice.exceptions;

/**
 * user defined excecption class for data base error
 */
public class ErrorWithConnectingToTheDataBase extends Exception {
    private String message;

    public ErrorWithConnectingToTheDataBase(String message){
        super(message);
        this.message=message;
    }
}
