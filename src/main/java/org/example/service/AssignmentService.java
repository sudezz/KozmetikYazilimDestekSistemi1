package org.example.service;

import org.example.model.Assignment;
import org.example.model.SupportRequest;
import org.example.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AssignmentService {

    private List<Assignment> assignments = new ArrayList<>();

    public Assignment assignRequest(SupportRequest request, User specialist) {
        Assignment assignment = new Assignment(
                assignments.size() + 1,
                request,
                specialist,
                LocalDateTime.now()
        );

        assignments.add(assignment);
        return assignment;
    }

    public List<Assignment> getAllAssignments() {
        return assignments;
    }
}