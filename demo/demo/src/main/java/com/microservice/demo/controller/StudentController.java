package com.microservice.demo.controller;

import com.microservice.demo.exception.StudentExistsException;
import com.microservice.demo.exception.StudentNotFoundException;
import com.microservice.demo.model.StudentDetails;
import com.microservice.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// Parent End-point
@RequestMapping("StudentDetails")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<?> studentRegister(@RequestBody StudentDetails details) throws StudentExistsException {
        return new ResponseEntity<>(studentService.addStudentDetails(details), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getStudentDetails(@RequestParam(value = "name",required = false) String name,
                                               @RequestParam(value = "email",required = false) String email) throws StudentNotFoundException{
        return new ResponseEntity<>(studentService.getStudentDetails(name,email),HttpStatus.OK);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteStudentDetails(@PathVariable String email) throws StudentNotFoundException{
        return new ResponseEntity<>(studentService.deleteStudentDetails(email),HttpStatus.OK);
    }

}
