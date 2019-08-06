package com.stackroute.trackservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ErrorWithConnectingToTheDataBase.class)
    public ResponseEntity<Object> errorWithConnectingToTheDataBase(final ErrorWithConnectingToTheDataBase ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TrackNotAvailable.class)
    public ResponseEntity<Object> trackNotAvailable(final TrackNotAvailable ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TrackAlreadyExistsException.class)
    public ResponseEntity<Object> trackAlreadyExistsException(final TrackAlreadyExistsException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
    }
}
