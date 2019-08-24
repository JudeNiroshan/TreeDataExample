package com.holidu.interview.assignment.api.rest;

import com.holidu.interview.assignment.exception.DataParseException;
import com.holidu.interview.assignment.exception.DataProviderCommunicationException;
import com.holidu.interview.assignment.model.RestErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;

/**
 * Abstract class that handles common known exceptions. All controllers
 * must extend this abstract class.
 */
@ControllerAdvice
public abstract class AbstractRestHandler {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    @ExceptionHandler(DataProviderCommunicationException.class)
    public
    @ResponseBody
    RestErrorInfo handleIncompatibleJsonResponse(DataProviderCommunicationException ex,
                                                 WebRequest request,
                                                 HttpServletResponse response) {
        log.info("Converting Incompatible JSON response exception to RestResponse : " + ex.getMessage());

        return new RestErrorInfo(ex, "Data source broken!!! " + ex.getIntegrationName());
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataParseException.class)
    public
    @ResponseBody
    RestErrorInfo handleDataParseException(DataParseException ex,
                                                 WebRequest request,
                                                 HttpServletResponse response) {
        log.info("JSON parse exception : " + ex.getMessage());

        return new RestErrorInfo(ex, "Data structure broken! ");
    }
}
