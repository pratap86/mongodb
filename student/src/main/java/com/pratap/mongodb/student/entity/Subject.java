package com.pratap.mongodb.student.entity;

import org.springframework.data.mongodb.core.mapping.Field;

public class Subject {

    @Field(name = "name")
    private String name;

    @Field(name = "marks_obtained")
    private int marksObtained;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(int marksObtained) {
        this.marksObtained = marksObtained;
    }
}
