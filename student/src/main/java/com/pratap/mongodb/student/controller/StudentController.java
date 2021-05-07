package com.pratap.mongodb.student.controller;

import com.pratap.mongodb.student.dto.StudentDto;
import com.pratap.mongodb.student.model.request.StudentRequestModel;
import com.pratap.mongodb.student.model.response.StudentResponseModel;
import com.pratap.mongodb.student.service.StudentService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public StudentResponseModel createStudent(@RequestBody StudentRequestModel studentRequestModel) throws Exception {

        LOGGER.info("Controller going to trigger the Service layer, req is: ", studentRequestModel);

        StudentDto studentDto = modelMapper.map(studentRequestModel, StudentDto.class);
        StudentDto fetchedDto = studentService.create(studentDto);

        LOGGER.info("Response received from Service", fetchedDto);
        return modelMapper.map(fetchedDto, StudentResponseModel.class);
    }
}
