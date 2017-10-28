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
 * Created at 17/9/17 下午7:03
 */
public class JieQiBean {


    /**
     * jieqiid : 1
     * name : 立春
     * date : 02.04
     * jianjie : 　　立春，是二十四节气之一，又称“打春”。“立”是“开始”的意思，中国以立春为春季的开始，每年2月4日或5日太阳到达黄经315度时为立春，《月令七十二候集解》：“正月节，立，建始也，立夏秋冬同。
     * youlai : 　　立春作为节令早在春秋时就有了，那时一年中有立春、立夏、立秋、立冬、春分、秋分、夏至、冬至八个节令，到了《礼记·月令》一书和西汉刘安所著的《淮南子·天文训》中，才有24个节气的记载。
     * xisu : 　　立春亦称“打春”、“咬春”，又叫“报春”。
     * yangsheng : 　　饮食调养方面要考虑春季阳气初生，宜食辛甘发散之品，不宜食酸收之味。
     * pic : http://m.46644.com/jieqi/static/images/jieqi/lc.png
     * list : [{"time":"2016-02-04 17:46:00"},{"time":"2017-02-03 23:34:01"},{"time":"2018-02-04
     * 05:28:25"},{"time":"2019-02-04 11:14:14"},{"time":"2020-02-04 17:03:12"}]
     */

    private String         jieqiid;
    private String         name;
    private String         date;
    private String         jianjie;
    private String         youlai;
    private String         xisu;
    private String         yangsheng;
    private String         pic;
    private List<ListBean> list;

    public String getJieqiid() {
        return jieqiid;
    }

    public void setJieqiid(String jieqiid) {
        this.jieqiid = jieqiid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }

    public String getYoulai() {
        return youlai;
    }

    public void setYoulai(String youlai) {
        this.youlai = youlai;
    }

    public String getXisu() {
        return xisu;
    }

    public void setXisu(String xisu) {
        this.xisu = xisu;
    }

    public String getYangsheng() {
        return yangsheng;
    }

    public void setYangsheng(String yangsheng) {
        this.yangsheng = yangsheng;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * time : 2016-02-04 17:46:00
         */

        private String time;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
