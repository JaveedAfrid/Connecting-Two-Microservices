package com.microservice.demo.service;

import com.microservice.demo.exception.StudentExistsException;
import com.microservice.demo.exception.StudentNotFoundException;
import com.microservice.demo.model.StudentDetails;

import java.util.List;

public interface StudentService {

    StudentDetails addStudentDetails(StudentDetails details) throws StudentExistsException;

    List<StudentDetails> getStudentDetails(String name, String email) throws StudentNotFoundException;

    StudentDetails deleteStudentDetails(String email) throws StudentNotFoundException;
}
