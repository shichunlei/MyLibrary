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
 * Created at 17/8/20 下午1:32
 */
public class BaseBean {

    private String             message;
    private int                status;
    private List<CarBean>      cars;
    private List<GirlsBean>    girls;
    private List<QuestionBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CarBean> getCars() {
        return cars;
    }

    public void setCars(List<CarBean> cars) {
        this.cars = cars;
    }

    public List<GirlsBean> getGirls() {
        return girls;
    }

    public void setGirls(List<GirlsBean> girls) {
        this.girls = girls;
    }

    public List<QuestionBean> getData() {
        return data;
    }

    public void setData(List<QuestionBean> data) {
        this.data = data;
    }
}
