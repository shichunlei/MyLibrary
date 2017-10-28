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
 * Created at 17/9/17 下午5:56
 */
public class Calendar {

    /**
     * nongli : 农历二〇一五年九月初十
     * taishen : 厨灶厕外西南
     * wuxing : 路旁土
     * chong : 冲（乙丑）牛
     * sha : 煞西
     * jiri : 朱雀（黑道）收日
     * zhiri : 朱雀（黑道凶日）
     * xiongshen : 地曩 月刑 河魁 五虚 朱雀
     * jishenyiqu : 天德合 母仓 不将 玉宇 月德合
     * caishen : 正东
     * xishen : 西南
     * fushen : 西南
     * suici : ["乙未年","丙戌月","辛未日"]
     * yi : ["祭祀","冠笄","移徙","会亲友","纳财","理发","捕捉"]
     * ji : ["嫁娶","开市","开池","作厕","破土"]
     */

    private String       nongli;
    private String       taishen;
    private String       wuxing;
    private String       chong;
    private String       sha;
    private String       jiri;
    private String       zhiri;
    private String       xiongshen;
    private String       jishenyiqu;
    private String       caishen;
    private String       xishen;
    private String       fushen;
    private List<String> suici;
    private List<String> yi;
    private List<String> ji;

    public String getNongli() {
        return nongli;
    }

    public void setNongli(String nongli) {
        this.nongli = nongli;
    }

    public String getTaishen() {
        return taishen;
    }

    public void setTaishen(String taishen) {
        this.taishen = taishen;
    }

    public String getWuxing() {
        return wuxing;
    }

    public void setWuxing(String wuxing) {
        this.wuxing = wuxing;
    }

    public String getChong() {
        return chong;
    }

    public void setChong(String chong) {
        this.chong = chong;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getJiri() {
        return jiri;
    }

    public void setJiri(String jiri) {
        this.jiri = jiri;
    }

    public String getZhiri() {
        return zhiri;
    }

    public void setZhiri(String zhiri) {
        this.zhiri = zhiri;
    }

    public String getXiongshen() {
        return xiongshen;
    }

    public void setXiongshen(String xiongshen) {
        this.xiongshen = xiongshen;
    }

    public String getJishenyiqu() {
        return jishenyiqu;
    }

    public void setJishenyiqu(String jishenyiqu) {
        this.jishenyiqu = jishenyiqu;
    }

    public String getCaishen() {
        return caishen;
    }

    public void setCaishen(String caishen) {
        this.caishen = caishen;
    }

    public String getXishen() {
        return xishen;
    }

    public void setXishen(String xishen) {
        this.xishen = xishen;
    }

    public String getFushen() {
        return fushen;
    }

    public void setFushen(String fushen) {
        this.fushen = fushen;
    }

    public List<String> getSuici() {
        return suici;
    }

    public void setSuici(List<String> suici) {
        this.suici = suici;
    }

    public List<String> getYi() {
        return yi;
    }

    public void setYi(List<String> yi) {
        this.yi = yi;
    }

    public List<String> getJi() {
        return ji;
    }

    public void setJi(List<String> ji) {
        this.ji = ji;
    }

    @Override
    public String toString() {
        return "Calendar{"
                + "nongli='"
                + nongli
                + '\''
                + ", taishen='"
                + taishen
                + '\''
                + ", wuxing='"
                + wuxing
                + '\''
                + ", chong='"
                + chong
                + '\''
                + ", sha='"
                + sha
                + '\''
                + ", jiri='"
                + jiri
                + '\''
                + ", zhiri='"
                + zhiri
                + '\''
                + ", xiongshen='"
                + xiongshen
                + '\''
                + ", jishenyiqu='"
                + jishenyiqu
                + '\''
                + ", caishen='"
                + caishen
                + '\''
                + ", xishen='"
                + xishen
                + '\''
                + ", fushen='"
                + fushen
                + '\''
                + ", suici="
                + suici
                + ", yi="
                + yi
                + ", ji="
                + ji
                + '}';
    }
}
