package com.example.SpringBootREST.exception;

import lombok.Data;

import java.util.Map;

@Data
public class ServiceException extends RuntimeException {
    private boolean success;
    private int status;
    private Map<String, Object> param;

    public ServiceException(){

    }

    public ServiceException(String message){
        super(message);
        this.status = 500;
        this.success = false;
    }
    public ServiceException(String message, Throwable cause) {
        super( message, cause );
        this.status = 500;
        //  this.msg = message;
        this.success = false;
    }
    public ServiceException(String msg, int code) {
        super(msg);
        this.status = code;
        //  this.msg = msg;
        this.success = false;
    }
    public ServiceException(String msg, int code, Map<String , Object> busParam) {
        super(msg);
        this.status = code;
        // this.msg = msg;
        this.success = false;
        this.param = busParam;
    }
}
