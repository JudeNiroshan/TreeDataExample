package com.holidu.interview.assignment.api.rest;

import com.holidu.interview.assignment.service.TreeService;
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
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/search")
public class TreeController {

    @Autowired
    private TreeService treeService;

    @RequestMapping(value = "/{x}/{y}/{radius}",
            method = RequestMethod.GET,
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    Map<String, Long> getTreeCountInArea(@PathVariable("x") Double x,
                                         @PathVariable("y") Double y,
                                         @PathVariable("radius") Double radius,
                                         HttpServletRequest request, HttpServletResponse response) throws Exception {
        return treeService.getTreeStatistics(x, y, radius);
    }
}
