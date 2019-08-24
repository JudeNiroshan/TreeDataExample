package com.holidu.interview.assignment.exception;

/**
 * Represent parsing exceptions that can happen with the received
 * json response from 3rd party data providers
 */
public class DataParseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
