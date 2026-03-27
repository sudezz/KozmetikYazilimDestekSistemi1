package org.example.model;

import java.time.LocalDateTime;

public class Assignment {

    private int id;
    private SupportRequest request;
    private User specialist;
    private LocalDateTime assignedAt;

    public Assignment() {
    }

    public Assignment(int id, SupportRequest request, User specialist, LocalDateTime assignedAt) {
        this.id = id;
        this.request = request;
        this.specialist = specialist;
        this.assignedAt = assignedAt;
    }

    public int getId() {
        return id;
    }

    public SupportRequest getRequest() {
        return request;
    }

    public User getSpecialist() {
        return specialist;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }
}