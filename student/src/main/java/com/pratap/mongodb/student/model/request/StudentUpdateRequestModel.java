package com.pratap.mongodb.student.model.request;

public class StudentUpdateRequestModel {

    private String name;
    private String email;


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

    @Override
    public String toString() {
        return "StudentUpdateRequestModel{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
