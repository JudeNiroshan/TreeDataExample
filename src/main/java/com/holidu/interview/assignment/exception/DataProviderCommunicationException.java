package com.holidu.interview.assignment.exception;

/**
 * Represents any communication failure that could happen with
 * 3rd party data providers
 */
public class DataProviderCommunicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String integrationName = null;

    private String integrationEndpoint = null;

    public DataProviderCommunicationException(String message,
                                              Throwable cause,
                                              String integrationName,
                                              String integrationEndpoint) {
        super(message, cause);
        this.integrationName = integrationName;
        this.integrationEndpoint = integrationEndpoint;
    }

    public String getIntegrationName() {
        return integrationName;
    }

    public String getIntegrationEndpoint() {
        return integrationEndpoint;
    }
}
