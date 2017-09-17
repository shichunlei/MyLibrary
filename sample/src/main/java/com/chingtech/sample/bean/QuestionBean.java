package com.chingtech.sample.bean;

import java.io.Serializable;

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
 * Created at 17/8/27 下午4:58
 */
public class QuestionBean implements Serializable {

    /**
     * id : 16
     * question_no : 1
     * name : 面对未来我很乐观
     * key :
     * category : CAS
     * sub_category : 心理方面
     * is_overdue : 0
     * question_type : radio
     * options : 从未有过;偶尔;经常;每天
     * score : 1;2;3;4
     * content :
     */

    private int    id;
    private int    question_no;
    private String name;
    private String key;
    private String category;
    private String sub_category;
    private int    is_overdue;
    private String question_type;
    private String options;
    private String score;
    private String content;

    public QuestionBean(int question_no, String name, String sub_category, String question_type,
            String options) {
        this.question_no = question_no;
        this.name = name;
        this.sub_category = sub_category;
        this.question_type = question_type;
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestion_no() {
        return question_no;
    }

    public void setQuestion_no(int question_no) {
        this.question_no = question_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSub_category() {
        return sub_category;
    }

    public void setSub_category(String sub_category) {
        this.sub_category = sub_category;
    }

    public int getIs_overdue() {
        return is_overdue;
    }

    public void setIs_overdue(int is_overdue) {
        this.is_overdue = is_overdue;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "QuestionBean{"
                + "id="
                + id
                + ", question_no="
                + question_no
                + ", name='"
                + name
                + '\''
                + ", key='"
                + key
                + '\''
                + ", category='"
                + category
                + '\''
                + ", sub_category='"
                + sub_category
                + '\''
                + ", is_overdue="
                + is_overdue
                + ", question_type='"
                + question_type
                + '\''
                + ", options='"
                + options
                + '\''
                + ", score='"
                + score
                + '\''
                + ", content='"
                + content
                + '\''
                + '}';
    }
}
