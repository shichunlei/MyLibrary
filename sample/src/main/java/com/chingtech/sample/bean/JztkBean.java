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
 * Created at 17/9/10 下午12:49
 */
public class JztkBean {

    /**
     * id : 11
     * question : 右侧标志警示前方道路有连续三个或三个以上的弯路。
     * answer : 2
     * item1 : 正确
     * item2 : 错误
     * item3 :
     * item4 :
     * explains : 反向弯路-用以警告车辆驾驶人减速慢行。设置位置为两反向圆曲线起点的外面，但不应进入相邻的圆曲线内。
     * url : http://images.juheapi.com/jztk/subject4/11.jpg
     */

    private String id;
    private String question;
    private String answer;
    private String item1;
    private String item2;
    private String item3;
    private String item4;
    private String explains;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public String getItem4() {
        return item4;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "{'id':'"
                + id
                + "\', 'question':'"
                + question
                + "\', 'answer':'"
                + answer
                + "\', 'item1':'"
                + item1
                + "\', 'item2':'"
                + item2
                + "\', 'item3':'"
                + item3
                + "\', 'item4':'"
                + item4
                + "\', 'explains':'"
                + explains
                + "\', 'url':'"
                + url
                + "\'}";
    }
}
