package com.microservice.demo.repository;

import com.microservice.demo.model.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentDetails,String> {
    boolean existsByEmail(String email);
    Optional<List<StudentDetails>> findByNameAndEmail(String name, String email);
    Optional<List<StudentDetails>> findByName(String name);
    Optional<List<StudentDetails>> findByEmail(String email);

}
