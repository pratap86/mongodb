package com.pratap.mongodb.student.exceptions;

public class StudentServiceException extends RuntimeException {

    public StudentServiceException(String message){
        super(message);
    }
}
