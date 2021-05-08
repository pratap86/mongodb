package com.pratap.mongodb.student.entity;

import org.springframework.data.mongodb.core.mapping.Field;

public class Department {

    @Field(name = "name")
    private String name;

    @Field(name = "location")
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
