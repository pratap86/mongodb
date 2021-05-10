package com.pratap.mongodb.student.repository;

import com.pratap.mongodb.student.entity.StudentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<StudentEntity, String> {

    StudentEntity findByEmail(String email);

    List<StudentEntity> findByName(String name);

    StudentEntity findByNameAndEmail(String name, String email);

    StudentEntity findByNameOrEmail(String name, String email);

    List<StudentEntity> findByDepartmentDepartmentName(String deptname);

    List<StudentEntity> findBySubjectsSubjectName (String subName);

    List<StudentEntity> findByEmailIsLike (String email);

    List<StudentEntity> findByNameStartsWith (String name);
}
