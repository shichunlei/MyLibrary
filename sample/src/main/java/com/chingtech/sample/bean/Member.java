package com.chingtech.sample.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;
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
 * Package com.chingtech.library.bean
 * Description:
 * Created by 师春雷
 * Created at 17/8/12 上午11:46
 */
@Entity
public class Member {

    @Property(nameInDb = "_id")
    @Id
    private long memberId;//用户ID

    private int memberSex;//性别

    private String memberNickname;//昵称
    @Unique
    private String memberMobile;//手机号

    @Generated(hash = 1179228046)
    public Member(long memberId, int memberSex, String memberNickname,
            String memberMobile) {
        this.memberId = memberId;
        this.memberSex = memberSex;
        this.memberNickname = memberNickname;
        this.memberMobile = memberMobile;
    }

    @Generated(hash = 367284327)
    public Member() {
    }

    @Override
    public String toString() {
        return "Member{"
                + "memberId="
                + memberId
                + ", memberSex="
                + memberSex
                + ", memberNickname='"
                + memberNickname
                + '\''
                + ", memberMobile='"
                + memberMobile
                + '\''
                + '}';
    }

    public long getMemberId() {
        return this.memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public int getMemberSex() {
        return this.memberSex;
    }

    public void setMemberSex(int memberSex) {
        this.memberSex = memberSex;
    }

    public String getMemberNickname() {
        return this.memberNickname;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public String getMemberMobile() {
        return this.memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }
}
