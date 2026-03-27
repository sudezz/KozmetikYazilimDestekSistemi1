package org.example.model;

public class ItUzmaniBuilder implements UserBuilder {
    private ItUzmani user;

    public ItUzmaniBuilder() { this.user = new ItUzmani(); }

    @Override public UserBuilder setId(int id) { this.user.setId(id); return this; }
    @Override public UserBuilder setFullName(String fullName) { this.user.setFullName(fullName); return this; }
    @Override public UserBuilder setEmail(String email) { this.user.setEmail(email); return this; }
    @Override public UserBuilder setPassword(String password) { this.user.setPassword(password); return this; }
    @Override public UserBuilder setDepartmentId(int departmentId) { this.user.setDepartmentId(departmentId); return this; }
    @Override public UserBuilder setDepartmentName(String departmentName) { this.user.setDepartmentName(departmentName); return this; }
    @Override
    public UserBuilder setEmployeeNo(String employeeNo) {
        this.user.setEmployeeNo(employeeNo);
        return this;
    }

    @Override public User build() { return this.user; }
}