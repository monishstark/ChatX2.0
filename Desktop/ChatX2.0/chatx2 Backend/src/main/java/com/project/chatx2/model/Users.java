package com.project.chatx2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Users {
    @Id
    private int id;
    private String name;
    private int userid;
    private String avatar;
    private String phone;

    public Users() {
    }

    public Users(String name, int userid, String avatar, String phone) {
        this.name = name;
        this.userid = userid;
        this.avatar = avatar;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
