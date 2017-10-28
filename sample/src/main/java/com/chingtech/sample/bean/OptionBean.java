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
 * Encyclopedia
 * Package com.chingtech.encyclopedia.bean
 * Description:
 * Created by 师春雷
 * Created at 17/9/10 下午2:15
 */
public class OptionBean {

    private String options;

    private String no;

    public OptionBean(String no, String options) {
        this.options = options;
        this.no = no;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
