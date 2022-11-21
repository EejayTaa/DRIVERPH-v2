package com.ph.DriverPH.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Response API formats.
 *
 * @author Eejay Ta-a
 */
public class ResponseHandler {

    public static ResponseEntity OK() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", HttpStatus.OK.name());
        response.put("code", HttpStatus.OK.value());
        response.put("data", Collections.emptyList());

        return new ResponseEntity(response, HttpStatus.OK);
    }

    public static ResponseEntity OK(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", HttpStatus.OK.name());
        response.put("code", HttpStatus.OK.value());
        response.put("data", data);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    public static ResponseEntity CREATED() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", HttpStatus.CREATED.name());
        response.put("code", HttpStatus.CREATED.value());
        response.put("data", Collections.emptyList());

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    public static ResponseEntity CREATED(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", HttpStatus.CREATED.name());
        response.put("code", HttpStatus.CREATED.value());
        response.put("data", data);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    public static ResponseEntity NOT_FOUND() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", HttpStatus.NOT_FOUND.name());
        response.put("code", HttpStatus.NOT_FOUND.value());
        response.put("data", Collections.emptyList());

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity NOT_FOUND(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", HttpStatus.NOT_FOUND.name());
        response.put("code", HttpStatus.NOT_FOUND.value());
        response.put("data", data);

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity NOT_FOUND(Object data, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("code", HttpStatus.NOT_FOUND.value());
        response.put("data", data);

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }


    public static ResponseEntity BAD_REQUEST() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", HttpStatus.BAD_REQUEST.name());
        response.put("code", HttpStatus.BAD_REQUEST.value());
        response.put("data", Collections.emptyList());

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity BAD_REQUEST(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", HttpStatus.BAD_REQUEST.name());
        response.put("code", HttpStatus.BAD_REQUEST.value());
        response.put("data", data);

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity BAD_REQUEST(Object data, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("code", HttpStatus.BAD_REQUEST.value());
        response.put("data", data);

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity FORBIDDEN(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", HttpStatus.FORBIDDEN.name());
        response.put("code", HttpStatus.FORBIDDEN.value());
        response.put("data", data);

        return new ResponseEntity(response, HttpStatus.FORBIDDEN);
    }
    public static ResponseEntity FORBIDDEN() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", HttpStatus.FORBIDDEN.name());
        response.put("code", HttpStatus.FORBIDDEN.value());
        response.put("data", Collections.emptyList());

        return new ResponseEntity(response, HttpStatus.FORBIDDEN);
    }


}
