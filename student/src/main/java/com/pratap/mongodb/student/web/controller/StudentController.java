package com.pratap.mongodb.student.web.controller;

import com.pratap.mongodb.student.exceptions.StudentServiceException;
import com.pratap.mongodb.student.model.request.StudentRequestModel;
import com.pratap.mongodb.student.model.request.StudentUpdateRequestModel;
import com.pratap.mongodb.student.model.response.StudentResponseModel;
import com.pratap.mongodb.student.service.StudentService;
import com.pratap.mongodb.student.utils.JsonUtils;
import com.pratap.mongodb.student.web.dto.StudentDto;
import com.pratap.mongodb.student.web.dto.StudentUpdateDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    private StudentService studentService;

    private ModelMapper modelMapper;

    @Autowired
    public StudentController(StudentService studentService, ModelMapper modelMapper){
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentResponseModel> createStudent(@RequestBody StudentRequestModel studentRequestModel) throws Exception {

        LOGGER.info("methodName {}","createStudent()");
        LOGGER.info("RequestType {}", "POST");
        LOGGER.info("Controller going to trigger the Service layer with req is StudentRequestModel:\n{}", JsonUtils.prettyPrintJson(studentRequestModel));
        StudentDto studentDto = modelMapper.map(studentRequestModel, StudentDto.class);

        StudentDto studentCreated = studentService.createStudent(studentDto);
        LOGGER.info("Response received from Service, StudentDto\n{}", studentCreated);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(studentCreated.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public StudentResponseModel getStudentById(@PathVariable String id){
        LOGGER.info("methodName {}","getStudentById()");
        LOGGER.info("RequestType {}", "GET");
        LOGGER.info("Controller going to trigger the Service layer with id : {}", id);
        return modelMapper.map(studentService.findStudentById(id), StudentResponseModel.class);

    }

    @GetMapping
    public List<StudentResponseModel> getStudents(){

        LOGGER.info("methodName {}","getStudents()");
        LOGGER.info("RequestType {}", "GET");
        LOGGER.info("Controller going to trigger the Service layer to fetched all Student record");

       return studentService.getStudents().stream()
                .map(studentDto -> modelMapper.map(studentDto, StudentResponseModel.class)).collect(Collectors.toList());
    }

    @GetMapping("/pageBy")
    public List<StudentResponseModel> getStudentsWithPagination(@RequestParam int pageNumber, @RequestParam int pageSize){

        LOGGER.info("methodName {}","getStudentsWithPagination()");
        LOGGER.info("RequestType {}", "GET");
        LOGGER.info("Controller going to trigger the Service layer to fetched all Student record with Pagination, pageNumber : {} & pageSize : {}", pageNumber, pageSize);

        return studentService.getStudentsWithPagination(pageNumber, pageSize).stream()
                .map(studentDto -> modelMapper.map(studentDto, StudentResponseModel.class)).collect(Collectors.toList());
    }

    @GetMapping("/sortBy")
    public List<StudentResponseModel> getStudentsWithSorting(){

        LOGGER.info("methodName {}","getStudentsWithSorting()");
        LOGGER.info("RequestType {}", "GET");
        LOGGER.info("Controller going to trigger the Service layer to fetched all Student record with Sort");

        List<StudentDto> students = studentService.getStudentsWithSorting();

        return students.stream().map(studentDto -> modelMapper.map(studentDto, StudentResponseModel.class)).collect(Collectors.toList());
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<StudentResponseModel> updateStudentDetails(@PathVariable String id, @RequestBody StudentUpdateRequestModel studentUpdateRequestModel){

        LOGGER.info("methodName {}","updateStudentDetails()");
        LOGGER.info("RequestType {}", "PATCH");
        LOGGER.info("Controller going to trigger the Service layer to partially update Student record, StudentUpdateRequestModel \n{}", studentUpdateRequestModel);
        try {
            StudentDto fetchedStudentDetails = studentService.findStudentById(id);

            StudentUpdateDto studentUpdateDto = modelMapper.map(studentUpdateRequestModel, StudentUpdateDto.class);
            StudentDto updateStudentPartialDetails = studentService.updateStudentPartialDetails(studentUpdateDto, fetchedStudentDetails);
            StudentResponseModel studentResponseModel = modelMapper.map(updateStudentPartialDetails, StudentResponseModel.class);
            LOGGER.info("Partially updated");
            return ResponseEntity.ok(studentResponseModel);
        } catch (StudentServiceException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable String id){

        LOGGER.info("methodName {}","deleteStudentById()");
        LOGGER.info("RequestType {}", "DELETE");
        LOGGER.info("Controller going to trigger the Service layer to Delete Student record by Id : {}", id);

        studentService.deleteStudentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
