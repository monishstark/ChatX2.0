package com.project.chatx2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Messages {
    @Id
    private int id;
    private String inboxId;
    private String message;
    private LocalDateTime createdAt;
    private int userId;

    public Messages() {
    }

    public Messages(String inboxId, String message, LocalDateTime createdAt, int userId) {
        this.inboxId = inboxId;
        this.message = message;
        this.createdAt = createdAt;
        this.userId = userId;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
