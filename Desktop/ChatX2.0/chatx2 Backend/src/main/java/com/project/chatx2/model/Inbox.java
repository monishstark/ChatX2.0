package com.project.chatx2.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Inbox {
    @Id
    private int id;
    private String inboxId;
    private String lastMessage;
    private int lastSentUserId;

    public Inbox() {
    }

    public Inbox(String inboxId, String lastMessage, int lastSentUserId) {
        this.inboxId = inboxId;
        this.lastMessage = lastMessage;
        this.lastSentUserId = lastSentUserId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInboxId() {
        return inboxId;
    }

    public void setInboxId(String inboxId) {
        this.inboxId = inboxId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getLastSentUserId() {
        return lastSentUserId;
    }

    public void setLastSentUserId(int lastSentUserId) {
        this.lastSentUserId = lastSentUserId;
    }
}
