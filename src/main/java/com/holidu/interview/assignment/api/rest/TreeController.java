package com.holidu.interview.assignment.api.rest;

import com.holidu.interview.assignment.service.TreeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Min;
import java.util.Map;

/**
 * All tree data will be controlled here.
 */
@RestController
@RequestMapping(value = "/v1/search")
public class TreeController extends AbstractRestHandler {

    @Autowired
    private TreeDataService treeDataService;

    @RequestMapping(value = "/{x}/{y}/{radius}",
            method = RequestMethod.GET,
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    Map<String, Long> getTreeCountInArea(@PathVariable("x") @Min(0) Double x,
                                         @PathVariable("y") @Min(0) Double y,
                                         @PathVariable("radius") @Min(0) Double radius,
                                         HttpServletRequest request, HttpServletResponse response) {
        return treeDataService.getTreeStatistics(x, y, radius);
    }
}
