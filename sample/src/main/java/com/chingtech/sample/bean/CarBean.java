package com.chingtech.sample.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <p>
 * *    ***********    ***********    **
 * *    ***********    ***********    **
 * *    **             **             **
 * *    **             **             **
 * *    **             **             **
 * *    ***********    **             **
 * *    ***********    **             **
 * *             **    **             **
 * *             **    **             **
 * *             **    **             **
 * *    ***********    ***********    ***********
 * *    ***********    ***********    ***********
 * </p>
 * MyLibrary
 * Package com.chingtech.sample.bean
 * Description:
 * Created by 师春雷
 * Created at 17/8/20 下午1:30
 */
@Entity
public class CarBean {

    /**
     * id : 1
     * name : 奥迪
     * initial : A
     * parentid : 0
     * logo : http://api.jisuapi.com/car/static/images/logo/300/1.png
     * depth : 1
     */

    @Id
    @Property(nameInDb = "_id")
    private String id;
    private String name;
    private String initial;
    private String parentid;
    private String logo;
    private String depth;

    @Generated(hash = 2122112942)
    public CarBean(String id, String name, String initial, String parentid,
            String logo, String depth) {
        this.id = id;
        this.name = name;
        this.initial = initial;
        this.parentid = parentid;
        this.logo = logo;
        this.depth = depth;
    }

    @Generated(hash = 618925768)
    public CarBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }
}
