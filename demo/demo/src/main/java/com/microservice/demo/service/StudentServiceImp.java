package com.microservice.demo.service;

import com.microservice.demo.exception.StudentExistsException;
import com.microservice.demo.exception.StudentNotFoundException;
import com.microservice.demo.model.StudentDetails;
import com.microservice.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImp implements StudentService{

    private StudentRepository repository;

    public StudentServiceImp(StudentRepository repository){
        this.repository = repository;
    }

    @Override
    public StudentDetails addStudentDetails(StudentDetails details) throws StudentExistsException {
        if(repository.existsByEmail(details.getEmail())){
            throw new StudentExistsException("User Already exists with email");
        }
        // Adding records into Database
        return repository.save(details);
    }

    @Override
    public List<StudentDetails> getStudentDetails(String name, String email) throws StudentNotFoundException {
        System.out.println("Name :" + name + "Email :" + email);

        // Checking if the Name and Email is null
        if (name!=null && email!=null){
            // Finding the records by Name and Email
            Optional<List<StudentDetails>> byNameAndEmail = repository.findByNameAndEmail(name, email);
            // Checking if the variable is empty
            if (byNameAndEmail.get().isEmpty()){
                throw new StudentNotFoundException("No records were found");
            }
            // Returning the records found
            return byNameAndEmail.get();

            // Checking if the Name is null
        } else if (name!=null) {
            // Finding the records by Name
            Optional<List<StudentDetails>> byName = repository.findByName(name);
            // Checking if the variable is empty
            if (byName.get().isEmpty()){
                throw new StudentNotFoundException("No records were found");
            }
            // Returning the records found
            return byName.get();

            // Checking if the Email is null
        } else if (email!=null) {
            // Finding the records by Email
            Optional<List<StudentDetails>> byEmail = repository.findByEmail(email);
            // Checking if the variable is empty
            if (byEmail.isEmpty()){
                throw new StudentNotFoundException("No records were found");
            }
            // Returning the records found
            return byEmail.get();
        }
        // Returning all records if no params are given
         return getAllStudents();
    }

    @Override
    public StudentDetails deleteStudentDetails(String email) throws StudentNotFoundException {
        Optional<List<StudentDetails>> byEmail = repository.findByEmail(email);
        if (byEmail.isEmpty()){
            throw new StudentNotFoundException("Student with email -" + email + "- not found");
        }else {
            repository.delete(byEmail.get().get(0));
            return byEmail.get().get(0);
        }
    }


    // Getting all records from Database
    public List<StudentDetails> getAllStudents() throws StudentNotFoundException{
        System.out.println("Im in GetAllStudent");
        List<StudentDetails> studentDetailsList = repository.findAll();
        if(studentDetailsList.isEmpty()){
            throw new StudentNotFoundException("No records were found");
        }
        return studentDetailsList;
    }
}
