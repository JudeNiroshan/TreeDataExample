package com.holidu.interview.assignment.provider;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Interface can be use to integrate with any third-party data sources
 */
public interface AbstractTreeDataProvider {
    ObjectNode[] getData();
}
