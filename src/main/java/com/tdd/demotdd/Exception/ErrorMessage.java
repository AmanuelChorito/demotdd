package com.tdd.demotdd.Exception;

import lombok.Data;
import lombok.Getter;

import java.util.Date;
@Getter
public class ErrorMessage {
    private int statusCode;
    private Date timeStamp;
    private  String message;
    private String description;

    public ErrorMessage(int statusCode, Date timeStamp, String message, String description) {
        this.statusCode = statusCode;
        this.timeStamp = timeStamp;
        this.message = message;
        this.description = description;
    }
}
