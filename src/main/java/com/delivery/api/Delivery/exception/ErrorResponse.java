package com.delivery.api.Delivery.exception;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {
    private int status;
    private String message;
    private long timestamp;
    private String debugMessage;
    private List<ApiSubError> subErrors;
}