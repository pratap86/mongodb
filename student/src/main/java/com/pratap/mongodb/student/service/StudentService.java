package com.pratap.mongodb.student.service;

import com.pratap.mongodb.student.dto.StudentDto;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {

    StudentDto create(StudentDto studentDto) throws Exception;
}
