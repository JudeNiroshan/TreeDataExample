package com.holidu.interview.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

/**
 * Model object that represent a Tree entity
 */
public class Tree {
    @NonNull
    @JsonProperty("tree_id")
    private String treeId;
    @NonNull
    @JsonProperty("spc_common")
    private String treeName;
    @JsonProperty("x_sp")
    private Double x;
    @JsonProperty("y_sp")
    private Double y;

    public Tree() {
    }

    public Tree(String treeId, String treeName, Double x, Double y) {
        this.treeId = treeId;
        this.treeName = treeName;
        this.x = x;
        this.y = y;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }
}
