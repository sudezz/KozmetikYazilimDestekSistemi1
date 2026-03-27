package org.example.model;

public class StandardEmployee extends User {
    public StandardEmployee() {
        super();
    }
    @Override
    public String getRoleType() { return "STANDARD"; }
}