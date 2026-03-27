package org.example.model;

public abstract class User {
    protected int id;
    protected String fullName;
    protected String email;
    protected String employeeNo;
    protected String password;
    protected String departmentName;
    protected int departmentId;

    public User() {} // Bu boş metot çok kritik!

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEmployeeNo() { return employeeNo; }
    public void setEmployeeNo(String employeeNo) { this.employeeNo = employeeNo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }

    public abstract String getRoleType();
}