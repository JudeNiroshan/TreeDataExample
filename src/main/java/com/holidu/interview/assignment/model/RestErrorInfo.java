package com.holidu.interview.assignment.model;

/**
 * A sample class for adding error information in the response
 */
public class RestErrorInfo {
    public final String detail;
    public final String message;

    public RestErrorInfo(Exception ex, String detail) {
        this.message = ex.getLocalizedMessage();
        this.detail = detail;
    }
}