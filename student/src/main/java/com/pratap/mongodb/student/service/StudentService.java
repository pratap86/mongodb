package com.pratap.mongodb.student.service;

import com.pratap.mongodb.student.web.dto.StudentDto;
import com.pratap.mongodb.student.web.dto.StudentUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    StudentDto createStudent(StudentDto studentDto);

    StudentDto findStudentById(String id);

    List<StudentDto> getStudents();

    StudentDto updateStudentPartialDetails(StudentUpdateDto studentUpdateDto, StudentDto studentDto);
}
