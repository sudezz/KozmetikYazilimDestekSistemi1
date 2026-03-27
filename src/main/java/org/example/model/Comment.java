package org.example.model;

import java.time.LocalDateTime;

public class Comment {

    private int id;
    private SupportRequest request;
    private User user;
    private String message;
    private LocalDateTime createdAt;

    public Comment() {
    }

    public Comment(int id, SupportRequest request, User user, String message, LocalDateTime createdAt) {
        this.id = id;
        this.request = request;
        this.user = user;
        this.message = message;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public SupportRequest getRequest() {
        return request;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}