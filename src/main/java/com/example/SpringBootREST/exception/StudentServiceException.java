package com.example.SpringBootREST.exception;

public class StudentServiceException extends RuntimeException {

    public StudentServiceException(String message) {
        super(message);
    }

}
