package com.ph.DriverPH.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



import java.util.*;

@ControllerAdvice
@RestController
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public final ResponseEntity exception(ServiceException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("code", ex.getCode().value());
        response.put("data", ex.getData());

        return new ResponseEntity(response, ex.getCode());
    }


}
