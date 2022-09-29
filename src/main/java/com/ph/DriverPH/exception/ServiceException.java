package com.ph.DriverPH.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
public class ServiceException extends RuntimeException {
    private boolean success;
    private HttpStatus status;
    private Map<String, Object> param;

    public ServiceException(){

    }

    public ServiceException(String message){
        super(message);
        this.success = false;
    }

    public ServiceException(String message, Throwable cause) {
        super( message, cause );
        this.success = false;
    }

    public ServiceException(String msg, HttpStatus code) {
        super(msg);
        this.status = code;
        this.success = false;
    }

    public ServiceException(String msg, HttpStatus code, Map<String , Object> busParam) {
        super(msg);
        this.status = code;
        this.success = false;
        this.param = busParam;
    }
}
