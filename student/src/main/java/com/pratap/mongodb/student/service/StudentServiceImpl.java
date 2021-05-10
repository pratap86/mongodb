package com.pratap.mongodb.student.service;

import com.pratap.mongodb.student.entity.StudentEntity;
import com.pratap.mongodb.student.entity.Subject;
import com.pratap.mongodb.student.exceptions.StudentServiceException;
import com.pratap.mongodb.student.repository.StudentRepository;
import com.pratap.mongodb.student.utils.JsonUtils;
import com.pratap.mongodb.student.web.dto.StudentDto;
import com.pratap.mongodb.student.web.dto.StudentUpdateDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public StudentDto createStudent(StudentDto studentDto) {

        LOGGER.info("Method {}", "createStudent()");
        LOGGER.info("Service going create new record with StudentDto \n{}", JsonUtils.prettyPrintJson(studentDto));

        if (studentRepository.findByEmail(studentDto.getEmail()) != null) {
            //log the exception and throw
            LOGGER.error("mail id already exist, mail : {}",studentDto.getEmail());
            throw new StudentServiceException("Duplicate mail Id : "+studentDto.getEmail());
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
        LOGGER.info("Service going to load all Student record");
        List<StudentEntity> studentEntities = studentRepository.findAll();
        LOGGER.info("Fetched Student response, List<StudentEntity> \n{}", JsonUtils.prettyPrintJson(studentEntities));
        return studentEntities.stream()
                .map(studentEntity -> modelMapper.map(studentEntity, StudentDto.class)).collect(Collectors.toList());
    }

    @Override
    public StudentDto updateStudentPartialDetails(StudentUpdateDto studentUpdateDto, StudentDto studentDto) {

        LOGGER.info("Method {}", "updateStudentDetails()");

        StudentEntity studentEntity = modelMapper.map(studentDto, StudentEntity.class);
        if (studentUpdateDto.getName()!=null)
            studentEntity.setName(studentUpdateDto.getName());
        if (studentUpdateDto.getEmail()!= null)
            studentEntity.setEmail(studentUpdateDto.getEmail());

        StudentEntity updatedStudent = studentRepository.save(studentEntity);
        return modelMapper.map(updatedStudent, StudentDto.class);
    }
}
