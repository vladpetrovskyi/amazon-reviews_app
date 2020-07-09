package com.vp.amazonreviewsapp.exception;

public class DataProcessingException extends Exception {
    public DataProcessingException(String message) {
        super(message);
    }

    public DataProcessingException(String message, Throwable e) {
        super(message, e);
    }
}
