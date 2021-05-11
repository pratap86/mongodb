package com.pratap.mongodb.student.service;

import com.pratap.mongodb.student.entity.StudentEntity;
import com.pratap.mongodb.student.web.dto.StudentDto;
import com.pratap.mongodb.student.web.dto.StudentUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    StudentDto createStudent(StudentDto studentDto) throws Exception;

    StudentDto findStudentById(String id);

    List<StudentDto> getStudents();

    List<StudentDto> getStudentsWithPagination(int pageNumber, int pageSize);

    List<StudentDto> getStudentsWithSorting();

    StudentDto updateStudentPartialDetails(StudentUpdateDto studentUpdateDto, StudentDto studentDto);

    void deleteStudentById(String id);

    List<StudentDto> getStudentsByName(String name);

    StudentDto getStudentByNameAndEmail(String name, String email);

    StudentDto getStudentByNameOrEmail(String name, String email);

    List<StudentDto> getStudentsByDepartmentName(String departmentName);

    List<StudentDto> getStudentsBySubjectName(String subjectName);

    List<StudentDto> getStudentsByEmailIsLike(String email);

    List<StudentDto> getStudentsByNameStartsWith(String name);


}
