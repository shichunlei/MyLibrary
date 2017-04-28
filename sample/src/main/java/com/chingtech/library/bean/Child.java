package com.chingtech.library.bean;

/**
 * MyLibrary
 * Package com.chingtech.library.bean
 * Description:
 * Created by 师春雷
 * Created at 2017/4/28 16:52
 */
public class Child {

    private int id;

    private String name;

    private String url;

    public Child(){
    }

    public Child(String name, String url){
        this.name = name;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
