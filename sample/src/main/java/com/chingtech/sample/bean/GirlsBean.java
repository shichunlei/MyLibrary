package com.chingtech.sample.bean;

import java.util.List;

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
 * Created at 17/8/20 下午3:50
 */
public class GirlsBean {


    /**
     * avatar : http://img0.imgtn.bdimg.com/it/u=4118505357,1261059307&fm=26&gp=0.jpg
     * id : 91
     * name : 刘诗诗
     * pictures : ["http://cdn.duitang.com/uploads/item/201601/22/20160122190424_s54SC.png","http://d.paper.i4.cn/max/2017/04/12/15/1491983588124_449071.jpg","http://img5.duitang.com/uploads/item/201603/16/20160316122659_sh3Wf.png","http://d.paper.i4.cn/max/2016/07/29/10/1469761019331_530663.jpg","http://img0.imgtn.bdimg.com/it/u=2630296243,1295496247&fm=214&gp=0.jpg"]
     */

    private String       avatar;
    private int          id;
    private String       name;
    private List<String> pictures;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

}
