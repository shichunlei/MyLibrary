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
 * Created at 17/8/20 下午1:30
 */
public class CarBean {

    /**
     * initial : A
     * logoUrl : http://image.bitautoimg.com/bt/car/default/images/logo/masterbrand/png/100/m_9_100.png
     * masterId : 9
     * name : 奥迪
     * saleStatus : 1
     * uv : 2050725
     * weight : 90
     */

    private String initial;
    private String logoUrl;
    private int    masterId;
    private String name;
    private int    saleStatus;
    private Long    uv;
    private int    weight;

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(int saleStatus) {
        this.saleStatus = saleStatus;
    }

    public Long getUv() {
        return uv;
    }

    public void setUv(Long uv) {
        this.uv = uv;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "CarBean{"
                + "initial='"
                + initial
                + '\''
                + ", logoUrl='"
                + logoUrl
                + '\''
                + ", masterId="
                + masterId
                + ", name='"
                + name
                + '\''
                + ", saleStatus="
                + saleStatus
                + ", uv="
                + uv
                + ", weight="
                + weight
                + '}';
    }
}
