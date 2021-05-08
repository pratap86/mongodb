package com.pratap.mongodb.student.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pratap.mongodb.student.dto.StudentDto;
import com.pratap.mongodb.student.entity.StudentEntity;
import com.pratap.mongodb.student.repository.StudentRepository;
import com.pratap.mongodb.student.utils.JsonUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentDto create(StudentDto studentDto) throws JsonProcessingException {

        LOGGER.info("Method {}", "create()");
        LOGGER.info("Service going create new record with StudentDto \n{}", JsonUtils.prettyPrintJson(studentDto));
        if (studentRepository.findByEmail(studentDto.getEmail()) != null) {
            //log the exception and throw
            LOGGER.error("mail id already exist, mail : {}",studentDto.getEmail());
            throw new RuntimeException("Duplicate mail Id");
        }
        StudentEntity studentEntity = modelMapper.map(studentDto, StudentEntity.class);

        StudentEntity savedStudent = studentRepository.save(studentEntity);

        LOGGER.info("Student saved.");
        return modelMapper.map(savedStudent, StudentDto.class);
    }
}
