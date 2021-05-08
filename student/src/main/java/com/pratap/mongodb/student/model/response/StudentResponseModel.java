package com.pratap.mongodb.student.model.response;

import com.pratap.mongodb.student.entity.Department;
import com.pratap.mongodb.student.entity.Subject;

import java.util.List;

public class StudentResponseModel {

    private String id;
    private String name;
    private String email;
    private Department department;
    private List<Subject> subjects;
    private long percentage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public long getPercentage() {
        return percentage;
    }

    public void setPercentage(long percentage) {
        this.percentage = percentage;
    }
}
