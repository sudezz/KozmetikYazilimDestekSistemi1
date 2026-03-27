package org.example.model;

public interface UserBuilder {
    UserBuilder setId(int id);
    UserBuilder setFullName(String fullName);
    UserBuilder setEmail(String email);
    UserBuilder setEmployeeNo(String employeeNo);
    UserBuilder setPassword(String password);
    UserBuilder setDepartmentId(int departmentId);
    UserBuilder setDepartmentName(String departmentName);
    User build();
}