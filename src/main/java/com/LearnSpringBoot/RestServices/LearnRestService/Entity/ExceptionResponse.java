package com.LearnSpringBoot.RestServices.LearnRestService.Entity;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResponse {

    private Date timestamp;
    private String Description;
    private String Message;

    public ExceptionResponse(Date timestamp, String description, String message) {
        this.timestamp = timestamp;
        Description = description;
        Message = message;
    }
}
