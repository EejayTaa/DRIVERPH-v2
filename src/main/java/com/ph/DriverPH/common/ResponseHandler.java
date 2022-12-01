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

}
