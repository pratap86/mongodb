package com.pratap.mongodb.student.service;

import com.pratap.mongodb.student.entity.StudentEntity;
import com.pratap.mongodb.student.exceptions.StudentServiceException;
import com.pratap.mongodb.student.repository.StudentRepository;
import com.pratap.mongodb.student.utils.JsonUtils;
import com.pratap.mongodb.student.web.dto.StudentDto;
import com.pratap.mongodb.student.web.dto.StudentUpdateDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    private StudentRepository studentRepository;

    private ModelMapper modelMapper;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper){
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) throws Exception {

        LOGGER.info("Method {}", "createStudent()");
        LOGGER.info("Service going create new record with StudentDto \n{}", JsonUtils.prettyPrintJson(studentDto));

        if (studentRepository.findByEmail(studentDto.getEmail()) != null) {
            //log the exception and throw
            LOGGER.error("mail id already exist, mail : {}",studentDto.getEmail());
            throw new Exception("mail id already exist : "+studentDto.getEmail());
        }
        StudentEntity studentEntity = modelMapper.map(studentDto, StudentEntity.class);

        StudentEntity savedStudent = studentRepository.save(studentEntity);

        LOGGER.info("Student saved, StudentEntity \n{}", JsonUtils.prettyPrintJson(savedStudent));
        return modelMapper.map(savedStudent, StudentDto.class);
    }

    @Override
    public StudentDto findStudentById(String id) {
        LOGGER.info("Method {}", "findStudentById()");
        LOGGER.info("Service going to find Student record by ID \n{}", id);

        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(() -> new StudentServiceException("Student record Not Found By Id :" + id));

        LOGGER.info("Fetched StudentEntity \n{}", JsonUtils.prettyPrintJson(studentEntity));

        return modelMapper.map(studentEntity, StudentDto.class);
    }

    @Override
    public List<StudentDto> getStudents() {

        LOGGER.info("Method {}", "getStudents()");
        LOGGER.info("Service going to load all Student record ");

        List<StudentEntity> studentEntities = studentRepository.findAll();
        if (studentEntities.size() == 0)
            throw new StudentServiceException("No Records are available");
        LOGGER.info("Fetched Student response, List<StudentEntity> \n{}", JsonUtils.prettyPrintJson(studentEntities));
        return studentEntities.stream()
                .map(studentEntity -> modelMapper.map(studentEntity, StudentDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getStudentsWithPagination(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber -1, pageSize);

        List<StudentEntity> students = studentRepository.findAll(pageable).getContent();

        return students.stream().map(studentEntity -> modelMapper.map(studentEntity, StudentDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getStudentsWithSorting() {

        Sort sort = Sort.by(Sort.Direction.ASC, "name", "email");

        List<StudentEntity> students = studentRepository.findAll(sort);

        return students.stream().map(studentEntity -> modelMapper.map(studentEntity, StudentDto.class)).collect(Collectors.toList());
    }

    @Override
    public StudentDto updateStudentPartialDetails(StudentUpdateDto studentUpdateDto, StudentDto studentDto) {

        LOGGER.info("Method {}", "updateStudentDetails()");
        LOGGER.info("Service going to partial update Student record by StudentUpdateDto \n{}", JsonUtils.prettyPrintJson(studentUpdateDto));

        StudentEntity studentEntity = modelMapper.map(studentDto, StudentEntity.class);
        if (studentUpdateDto.getName()!=null)
            studentEntity.setName(studentUpdateDto.getName());
        if (studentUpdateDto.getEmail()!= null)
            studentEntity.setEmail(studentUpdateDto.getEmail());

        StudentEntity updatedStudent = studentRepository.save(studentEntity);
        return modelMapper.map(updatedStudent, StudentDto.class);
    }

    @Override
    public void deleteStudentById(String id) {

        LOGGER.info("Method {}", "deleteStudentById()");
        LOGGER.info("Going to delete Student record by Id : {}", id);
        studentRepository.findById(id).orElseThrow(() -> new StudentServiceException("Student Not Found By Id : "+id));
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentDto> getStudentsByName(String name) {

        LOGGER.info("Method {}", "getStudentsByName()");
        LOGGER.info("Going to get Students by their name : {}", name);
        List<StudentEntity> fetchedStudents = studentRepository.findByName(name);
        if (fetchedStudents == null)
            throw new StudentServiceException("No records associated with Student name :"+name);

        return fetchedStudents.stream().map(studentEntity -> modelMapper.map(studentEntity, StudentDto.class)).collect(Collectors.toList());
    }

    @Override
    public StudentDto getStudentByNameAndEmail(String name, String email) {

        LOGGER.info("Method {}", "getStudentByNameAndEmail()");
        LOGGER.info("Going to get Students by their name : {},  AND email : {}", name, email);
        StudentEntity savedStudent = studentRepository.findByNameAndEmail(name, email);
        if (savedStudent == null)
            throw new StudentServiceException("No records associated with Student name : "+name +" AND mail : "+email);

        return modelMapper.map(savedStudent, StudentDto.class);
    }

    @Override
    public StudentDto getStudentByNameOrEmail(String name, String email) {

        LOGGER.info("Method {}", "getStudentByNameOrEmail()");
        LOGGER.info("Going to get Students by their name : {},  OR email : {}", name, email);
        StudentEntity savedStudent = studentRepository.findByNameOrEmail(name, email);
        if (savedStudent == null)
            throw new StudentServiceException("No records associated with Student name : "+name +" OR mail : "+email);

        return modelMapper.map(savedStudent, StudentDto.class);
    }

    @Override
    public List<StudentDto> getStudentsByDepartmentName(String departmentName) {

        LOGGER.info("Method {}", "getStudentsByDepartmentName()");
        LOGGER.info("Going to get Students by Department name : {}", departmentName);

        List<StudentEntity> students = studentRepository.findByDepartmentName(departmentName);
        if (students == null)
            throw new StudentServiceException("No Student records associated with Department name : "+departmentName);

        return students.stream().map(studentEntity -> modelMapper.map(studentEntity, StudentDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getStudentsBySubjectName(String subjectName) {

        LOGGER.info("Method {}", "getStudentsBySubjectName()");
        LOGGER.info("Going to get Students by Subject name : {}", subjectName);

        List<StudentEntity> students = studentRepository.findBySubjectsName(subjectName);
        if (students == null)
            throw new StudentServiceException("No Student records associated with Subject name : "+subjectName);

        return students.stream().map(studentEntity -> modelMapper.map(studentEntity, StudentDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getStudentsByEmailIsLike(String email) {

        LOGGER.info("Method {}", "getStudentsByEmailIsLike()");
        LOGGER.info("Going to get Students by EmailIsLike : {}", email);

        List<StudentEntity> students = studentRepository.findByEmailIsLike(email);
        if (students == null)
            throw new StudentServiceException("No Student records associated with email is like : "+email);

        return students.stream().map(studentEntity -> modelMapper.map(studentEntity, StudentDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getStudentsByNameStartsWith(String name) {
        LOGGER.info("Method {}", "getStudentsByNameStartsWith()");
        LOGGER.info("Going to get Students by student name start with : {}", name);

        List<StudentEntity> students = studentRepository.findByNameStartsWith(name);
        if (students == null)
            throw new StudentServiceException("No Student records associated with name start with : "+name);

        return students.stream().map(studentEntity -> modelMapper.map(studentEntity, StudentDto.class)).collect(Collectors.toList());
    }
}
