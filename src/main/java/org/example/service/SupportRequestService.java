package org.example.service;

import org.example.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SupportRequestService {

    private List<SupportRequest> requests = new ArrayList<>();

    public SupportRequest createRequest(String subject, String description, User requester, Category category) {

        SupportRequest request = new SupportRequest();
        request.setId(requests.size() + 1);
        request.setSubject(subject);
        request.setDescription(description);
        request.setRequester(requester);
        request.setCategory(category);
        request.setCreatedAt(LocalDateTime.now());

        RequestStatus openStatus = new RequestStatus(1, "Açık", false);
        request.setStatus(openStatus);

        requests.add(request);

        return request;
    }

    public List<SupportRequest> getAllRequests() {
        return requests;
    }
}