package com.holidu.interview.assignment.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.holidu.interview.assignment.exception.DataParseException;
import com.holidu.interview.assignment.model.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This class defines all custom logic that manages the data
 * received from NewYorkCity Tree data provider.
 */
@Component
public class NycTreeDataHandler implements TreeDataHandler {

    @Autowired
    protected ObjectMapper objectMapper;

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
        double radiusPower = Math.pow(radius, 2);

        allTrees.parallelStream().filter(tree ->
                (Math.pow(tree.getX_sp() - x, 2) + Math.pow(tree.getY_sp() - y, 2)) <= radiusPower
        ).forEach(tree -> concurrentMap.put(tree.getSpc_common(),
                concurrentMap.getOrDefault(tree.getSpc_common(), 0L)+1));
        return concurrentMap;
    }

}
