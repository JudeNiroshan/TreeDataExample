package com.holidu.interview.assignment.provider;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Interface can be use to integrate with any 3rd party
 * tree data sources
 */
public interface TreeDataProvider {

    /**
     * Fetch data as an array of ObjectNode
     * @return ObjectNode[]
     */
    ObjectNode[] getData();
}
