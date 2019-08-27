package com.holidu.interview.assignment.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.holidu.interview.assignment.exception.DataParseException;
import com.holidu.interview.assignment.model.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

/**
 * This class defines all custom logic that manages the data
 * received from NewYorkCity Tree data provider.
 */
@Component
public class NycTreeDataHandler implements TreeDataHandler {

    @Autowired
    protected ObjectMapper objectMapper;

    private static final double FEET_TO_METERS = 0.305;

    @Override
    public Set<Tree> getAllTrees(Supplier<ObjectNode[]> treeDataSupplier) {

        try {
            return objectMapper.convertValue(treeDataSupplier.get(), new TypeReference<Set<Tree>>() {
            });
        } catch (IllegalArgumentException e) {
            throw new DataParseException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Long> findTreesInsideTheCircle(Set<Tree> allTrees, Double x, Double y, Double radius) {
        ConcurrentMap<String, Long> concurrentMap = new ConcurrentHashMap<String, Long>();
        double radiusPower = Math.pow(metersToFeet(radius), 2);

        allTrees.parallelStream().filter(tree ->
                (Math.pow(tree.getX() - x, 2) + Math.pow(tree.getY() - y, 2)) <= radiusPower
        ).forEach(tree -> concurrentMap.put(tree.getTreeName(),
                concurrentMap.getOrDefault(tree.getTreeName(), 0L)+1));
        return concurrentMap;
    }

    private double metersToFeet(double meters){
        return meters * FEET_TO_METERS;
    }

}
