package com.holidu.interview.assignment.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.holidu.interview.assignment.model.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class NycTreeDataHandler implements AbstractTreeDataHandler {

    @Autowired
    protected ObjectMapper objectMapper;

    @Override
    public Set<Tree> getAllTrees(Supplier<ObjectNode[]> treeDataSupplier) {

        return objectMapper.convertValue(treeDataSupplier.get(), new TypeReference<Set<Tree>>() {
        });
    }

    @Override
    public Map<String, Long> findTreesInsideTheCircle(Set<Tree> allTrees, Double x, Double y, Double radius) {

        return allTrees.stream().filter(tree ->
                (Math.pow(tree.getX_sp() - x, 2) + Math.pow(tree.getY_sp() - y, 2)) <= Math.pow(radius, 2)
        ).collect(Collectors.groupingBy(Tree::getSpc_common, Collectors.counting()));
    }

}
