package com.holidu.interview.assignment.support;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @RequestMapping(
            name = "healthCheckEndpoint",
            method = RequestMethod.GET,
            value = "/"
    )
    public String healthCheck() {
        return "Greetings from the Holidu interview assignment!";
    }

}
