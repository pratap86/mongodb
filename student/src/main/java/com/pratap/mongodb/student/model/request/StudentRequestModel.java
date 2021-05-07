package com.pratap.mongodb.student.model.request;

import com.pratap.mongodb.student.entity.Department;
import com.pratap.mongodb.student.entity.Subject;

import java.util.List;

public class StudentRequestModel {

    private String name;
    private String email;
    private Department department;
    private List<Subject> subjects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
