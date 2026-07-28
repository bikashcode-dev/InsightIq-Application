package com.InsightIQ.InsightIQ.commons.response;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private final boolean success;
    private String message;
    private T data;
    private int statusCode;
    private LocalDateTime timestamp;


    public ApiResponse( boolean success , String message, T data , int statusCode) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
    }
}
