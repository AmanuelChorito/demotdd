package com.tdd.demotdd.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


    @ControllerAdvice
    public class ControllerExceptionHandler {
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ErrorMessage>resourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        ErrorMessage message= new ErrorMessage(HttpStatus.NOT_FOUND.value(),new Date(),ex.getMessage(),request.getDescription(true));

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage>globalExceptionHandler(Exception ex, WebRequest request){
        ErrorMessage message= new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<ErrorMessage>(message,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
