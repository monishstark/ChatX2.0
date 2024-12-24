package com.project.chatx2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Inboxuser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String inboxId;


    public Inboxuser() {
    }

    public Inboxuser(int userId, String inboxId) {
        this.userId = userId;
        this.inboxId = inboxId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getInboxId() {
        return inboxId;
    }

    public void setInboxId(String inboxId) {
        this.inboxId = inboxId;
    }
}
