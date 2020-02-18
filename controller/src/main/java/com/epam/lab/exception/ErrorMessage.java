package com.epam.lab.exception;

public class ErrorMessage {
    private int errorStatus;
    private String message;

    public ErrorMessage(int errorStatus, String message) {
        this.errorStatus = errorStatus;
        this.message = message;
    }

    public int getErrorStatus() {
        return errorStatus;
    }

    public String getMessage() {
        return message;
    }
}
