package com.holidu.interview.assignment.handler;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.holidu.interview.assignment.model.Tree;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * process Tree data in customizable ways
 */
public interface AbstractTreeDataHandler {

    Set<Tree> getAllTrees(Supplier<ObjectNode[]> treeDataSupplier);

    Map<String, Long> findTreesInsideTheCircle(Set<Tree> allTrees, Double x, Double y, Double radius);
}
