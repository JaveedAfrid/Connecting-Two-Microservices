package com.microservice.demo.exception;

public class StudentExistsException extends RuntimeException {
    public StudentExistsException(String message){
        super(message);
    }
}
