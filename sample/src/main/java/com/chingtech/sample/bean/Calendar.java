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
    private String  year;
    private String  month;
    private String  day;
    private String  week;
    private String  lunaryear;
    private String  lunarmonth;
    private String  lunarday;
    private String  ganzhi;
    private String  shengxiao;
    private String  star;
    private Huangli huangli;

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

    public Huangli getHuangli() {
        return huangli;
    }

    public void setHuangli(Huangli huangli) {
        this.huangli = huangli;
    }

    public class Huangli {

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
}
