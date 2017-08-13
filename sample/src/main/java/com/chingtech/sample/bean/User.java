package com.chingtech.sample.bean;

import chingtech.library.utils.TimeUtils;
import java.util.Date;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;

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
 * Package com.chingtech.library.bean
 * Description:
 * Created by 师春雷
 * Created at 17/8/12 上午9:44
 */
@Entity
public class User {

    @Id
    private long id;

    @Property(nameInDb = "USERNAME")
    @NotNull
    private String name;
    @Unique
    private String mobile;

    private int gender;

    private int age;

    private Date birthday;

    @Transient
    private String remarks;

    @Generated(hash = 114272646)
    public User(long id, @NotNull String name, String mobile, int gender, int age, Date birthday) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.gender = gender;
        this.age = age;
        this.birthday = birthday;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "User{"
                + "id="
                + id
                + ", name='"
                + name
                + '\''
                + ", mobile='"
                + mobile
                + '\''
                + ", gender="
                + gender
                + ", age="
                + age
                + ", birthday=\'"
                + TimeUtils.formatDateTime(birthday, TimeUtils.DATE_FORMAT)
                + "\'}";
    }
}
