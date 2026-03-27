package org.example.model;

public class RequestStatus {
    private int id;
    private String name;
    private boolean isFinal;

    public RequestStatus() {
    }

    public RequestStatus(int id, String name, boolean isFinal) {
        this.id = id;
        this.name = name;
        this.isFinal = isFinal;
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

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    @Override
    public String toString() {
        return name;
    }
}