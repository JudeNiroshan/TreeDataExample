package com.holidu.interview.assignment.service;

import com.holidu.interview.assignment.handler.AbstractTreeDataHandler;
import com.holidu.interview.assignment.model.Tree;
import com.holidu.interview.assignment.provider.AbstractTreeDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class TreeService {

    @Autowired
    private AbstractTreeDataProvider dataProvider;

    @Autowired
    private AbstractTreeDataHandler abstractTreeDataHandler;

    public Map<String, Long> getTreeStatistics(Double x, Double y, Double radius) {
        Set<Tree> allTrees = abstractTreeDataHandler.getAllTrees(dataProvider::getData);
        return abstractTreeDataHandler.findTreesInsideTheCircle(allTrees, x, y, radius);
    }
}
