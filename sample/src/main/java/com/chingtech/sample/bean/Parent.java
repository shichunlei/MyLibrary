package com.chingtech.sample.bean;

import java.util.List;

/**
 * MyLibrary
 * Package com.chingtech.library.bean
 * Description:
 * Created by 师春雷
 * Created at 2017/4/28 16:52
 */
public class Parent {

    private int id;

    private String name;

    private List<Child> children;

    public Parent(String name, List<Child> children){
        this.name = name;
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
