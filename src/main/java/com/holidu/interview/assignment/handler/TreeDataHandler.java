package com.holidu.interview.assignment.handler;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.holidu.interview.assignment.model.Tree;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * Interface to provide custom tree data processing
 */
public interface TreeDataHandler {

    /**
     * Fetch all tree data and map them to a Set<Tree>
     * @param treeDataSupplier
     * @return
     */
    Set<Tree> getAllTrees(Supplier<ObjectNode[]> treeDataSupplier);

    /**
     * Given a point in cartesian plane; along with the radius, find
     * out the trees that falls within the circular area
     * @param allTrees
     * @param x
     * @param y
     * @param radius
     * @return
     */
    Map<String, Long> findTreesInsideTheCircle(Set<Tree> allTrees, Double x, Double y, Double radius);
}
