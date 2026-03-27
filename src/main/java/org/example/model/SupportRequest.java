package org.example.model;

import java.time.LocalDateTime;

public class SupportRequest {
    private int id;
    private String subject;
    private String description;
    private LocalDateTime createdAt;
    private User requester;
    private Category category;
    private RequestStatus status;

    public SupportRequest() {
    }

    public SupportRequest(int id, String subject, String description, LocalDateTime createdAt,
                          User requester, Category category, RequestStatus status) {
        this.id = id;
        this.subject = subject;
        this.description = description;
        this.createdAt = createdAt;
        this.requester = requester;
        this.category = category;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}