package com.holidu.interview.assignment.model;

import org.springframework.lang.NonNull;

/**
 * Model object that represent a Tree entity
 */
public class Tree {
    @NonNull
    private String tree_id;
    @NonNull
    private String spc_common;
    private Double x_sp;
    private Double y_sp;

    public Tree() {
    }

    public Tree(String tree_id, String spc_common, Double x_sp, Double y_sp) {
        this.tree_id = tree_id;
        this.spc_common = spc_common;
        this.x_sp = x_sp;
        this.y_sp = y_sp;
    }

    public String getSpc_common() {
        return spc_common;
    }

    public void setSpc_common(String spc_common) {
        this.spc_common = spc_common;
    }

    public Double getX_sp() {
        return x_sp;
    }

    public void setX_sp(Double x_sp) {
        this.x_sp = x_sp;
    }

    public Double getY_sp() {
        return y_sp;
    }

    public void setY_sp(Double y_sp) {
        this.y_sp = y_sp;
    }

    public String getTree_id() {
        return tree_id;
    }

    public void setTree_id(String tree_id) {
        this.tree_id = tree_id;
    }
}
