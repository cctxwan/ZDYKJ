package com.cc.listview_ch.model;

/**
 * model模型
 */
public class ModelInfo {

    //名字
    public String name;

    @Override
    public String toString() {
        return "ModelInfo{" +
                "name='" + name + '\'' +
                '}';
    }

    public ModelInfo() {}

    public ModelInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
