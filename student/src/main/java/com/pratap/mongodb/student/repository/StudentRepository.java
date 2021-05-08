package com.pratap.mongodb.student.repository;

import com.pratap.mongodb.student.entity.StudentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<StudentEntity, String> {

    StudentEntity findByEmail(String email);
}
