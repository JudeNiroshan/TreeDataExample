package com.holidu.interview.assignment.service;

import com.holidu.interview.assignment.handler.TreeDataHandler;
import com.holidu.interview.assignment.model.Tree;
import com.holidu.interview.assignment.provider.TreeDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

import static com.holidu.interview.assignment.config.CacheConfig.SAME_REQUEST_CACHE;

/**
 * Service serves all Tree data delegations
 */
@Service
public class TreeDataService {

    @Autowired
    private TreeDataProvider dataProvider;

    @Autowired
    private TreeDataHandler treeDataHandler;

    @Cacheable(SAME_REQUEST_CACHE)
    public Map<String, Long> getTreeStatistics(Double x, Double y, Double radius) {
        Set<Tree> allTrees = treeDataHandler.getAllTrees(dataProvider::getData);
        return treeDataHandler.findTreesInsideTheCircle(allTrees, x, y, radius);
    }
}
