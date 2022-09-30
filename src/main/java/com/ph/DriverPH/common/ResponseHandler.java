package com.ph.DriverPH.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity responseBuilder(HttpStatus httpStatus){
        Map<String, Object> response = new HashMap<>();
        response.put("message", httpStatus.name());
        response.put("code", httpStatus.value());
        response.put("data", null);

        return new ResponseEntity(response, httpStatus);
    }

    public static ResponseEntity responseBuilder(Object responseObject){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Success");
        response.put("code", HttpStatus.OK.value());
        response.put("data", responseObject);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    public static ResponseEntity responseBuilder(Object responseObject, HttpStatus httpStatus){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Success");
        response.put("code", httpStatus.value());
        response.put("data", responseObject);

        return new ResponseEntity(response, httpStatus);
    }

    public static ResponseEntity responseBuilder(String message, HttpStatus httpStatus, Object responseObject){
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("code", httpStatus.value());
        response.put("data", responseObject);

        return new ResponseEntity(response, httpStatus);
    }
}
