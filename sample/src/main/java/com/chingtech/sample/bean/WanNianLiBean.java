package com.chingtech.sample.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
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
 * Created at 17/9/17 下午6:17
 */
@Entity
public class WanNianLiBean {

    @Id
    private long id;

    private String date;
    private String nongli;
    private String week;
    private String shengxiao;
    private String star;
    private String taishen;
    private String wuxing;
    private String chong;
    private String sha;
    private String jiri;
    private String zhiri;
    private String xiongshen;
    private String jishenyiqu;
    private String caishen;
    private String xishen;
    private String fushen;

    private String suici;
    private String yi;
    private String ji;

    @Generated(hash = 591827358)
    public WanNianLiBean(long id, String date, String nongli, String week, String shengxiao,
            String star, String taishen, String wuxing, String chong, String sha, String jiri,
            String zhiri, String xiongshen, String jishenyiqu, String caishen, String xishen,
            String fushen, String suici, String yi, String ji) {
        this.id = id;
        this.date = date;
        this.nongli = nongli;
        this.week = week;
        this.shengxiao = shengxiao;
        this.star = star;
        this.taishen = taishen;
        this.wuxing = wuxing;
        this.chong = chong;
        this.sha = sha;
        this.jiri = jiri;
        this.zhiri = zhiri;
        this.xiongshen = xiongshen;
        this.jishenyiqu = jishenyiqu;
        this.caishen = caishen;
        this.xishen = xishen;
        this.fushen = fushen;
        this.suici = suici;
        this.yi = yi;
        this.ji = ji;
    }

    @Generated(hash = 1899388467)
    public WanNianLiBean() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNongli() {
        return this.nongli;
    }

    public void setNongli(String nongli) {
        this.nongli = nongli;
    }

    public String getWeek() {
        return this.week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getShengxiao() {
        return this.shengxiao;
    }

    public void setShengxiao(String shengxiao) {
        this.shengxiao = shengxiao;
    }

    public String getStar() {
        return this.star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getTaishen() {
        return this.taishen;
    }

    public void setTaishen(String taishen) {
        this.taishen = taishen;
    }

    public String getWuxing() {
        return this.wuxing;
    }

    public void setWuxing(String wuxing) {
        this.wuxing = wuxing;
    }

    public String getChong() {
        return this.chong;
    }

    public void setChong(String chong) {
        this.chong = chong;
    }

    public String getSha() {
        return this.sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getJiri() {
        return this.jiri;
    }

    public void setJiri(String jiri) {
        this.jiri = jiri;
    }

    public String getZhiri() {
        return this.zhiri;
    }

    public void setZhiri(String zhiri) {
        this.zhiri = zhiri;
    }

    public String getXiongshen() {
        return this.xiongshen;
    }

    public void setXiongshen(String xiongshen) {
        this.xiongshen = xiongshen;
    }

    public String getJishenyiqu() {
        return this.jishenyiqu;
    }

    public void setJishenyiqu(String jishenyiqu) {
        this.jishenyiqu = jishenyiqu;
    }

    public String getCaishen() {
        return this.caishen;
    }

    public void setCaishen(String caishen) {
        this.caishen = caishen;
    }

    public String getXishen() {
        return this.xishen;
    }

    public void setXishen(String xishen) {
        this.xishen = xishen;
    }

    public String getFushen() {
        return this.fushen;
    }

    public void setFushen(String fushen) {
        this.fushen = fushen;
    }

    public String getSuici() {
        return this.suici;
    }

    public void setSuici(String suici) {
        this.suici = suici;
    }

    public String getYi() {
        return this.yi;
    }

    public void setYi(String yi) {
        this.yi = yi;
    }

    public String getJi() {
        return this.ji;
    }

    public void setJi(String ji) {
        this.ji = ji;
    }

}
