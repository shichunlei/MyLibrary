package com.chingtech.sample.bean;

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
 * Created at 17/9/17 下午5:53
 */
public class JiSuBaseBean<T> {

    private String status;
    private String msg;
    public  T      result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * year : 2015
     * month : 10
     * day : 22
     * week : 四
     * lunaryear : 2015
     * lunarmonth : 九月
     * lunarday : 初十
     * ganzhi : 乙未
     * shengxiao : 羊
     * star : 天枰座
     */
    private String year;
    private String month;
    private String day;
    private String week;
    private String lunaryear;
    private String lunarmonth;
    private String lunarday;
    private String ganzhi;
    private String shengxiao;
    private String star;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getLunaryear() {
        return lunaryear;
    }

    public void setLunaryear(String lunaryear) {
        this.lunaryear = lunaryear;
    }

    public String getLunarmonth() {
        return lunarmonth;
    }

    public void setLunarmonth(String lunarmonth) {
        this.lunarmonth = lunarmonth;
    }

    public String getLunarday() {
        return lunarday;
    }

    public void setLunarday(String lunarday) {
        this.lunarday = lunarday;
    }

    public String getGanzhi() {
        return ganzhi;
    }

    public void setGanzhi(String ganzhi) {
        this.ganzhi = ganzhi;
    }

    public String getShengxiao() {
        return shengxiao;
    }

    public void setShengxiao(String shengxiao) {
        this.shengxiao = shengxiao;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "JiSuBaseBean{"
                + "status='"
                + status
                + '\''
                + ", msg='"
                + msg
                + '\''
                + ", result="
                + result
                + ", year='"
                + year
                + '\''
                + ", month='"
                + month
                + '\''
                + ", day='"
                + day
                + '\''
                + ", week='"
                + week
                + '\''
                + ", lunaryear='"
                + lunaryear
                + '\''
                + ", lunarmonth='"
                + lunarmonth
                + '\''
                + ", lunarday='"
                + lunarday
                + '\''
                + ", ganzhi='"
                + ganzhi
                + '\''
                + ", shengxiao='"
                + shengxiao
                + '\''
                + ", star='"
                + star
                + '\''
                + '}';
    }
}
