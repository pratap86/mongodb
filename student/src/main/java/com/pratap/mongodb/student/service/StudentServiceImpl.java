package com.pratap.mongodb.student.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pratap.mongodb.student.dto.StudentDto;
import com.pratap.mongodb.student.entity.StudentEntity;
import com.pratap.mongodb.student.repository.StudentRepository;
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

        LOGGER.info("Going to save Student");
        if (studentRepository.findByEmail(studentDto.getEmail()) != null)
            throw new RuntimeException("Record already exist");

        StudentEntity studentEntity = modelMapper.map(studentDto, StudentEntity.class);

        StudentEntity savedStudent = studentRepository.save(studentEntity);

        LOGGER.info("Student saved.");
        return modelMapper.map(savedStudent, StudentDto.class);
    }
}
