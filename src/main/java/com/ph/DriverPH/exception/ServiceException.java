package com.ph.DriverPH.exception;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


import java.util.Collections;


/**
 * @author Administrator
 */
@Getter
@Setter
public class ServiceException extends RuntimeException {

    private HttpStatus code;
    private Object data;

    public ServiceException(String message) {
        super(message);
        this.code = HttpStatus.BAD_REQUEST;
        this.data = Collections.EMPTY_LIST;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        this.code = HttpStatus.BAD_REQUEST;
        this.data = Collections.EMPTY_LIST;
    }

    public ServiceException(String message, HttpStatus code) {
        super(message);
        this.code = code;
        this.data = Collections.EMPTY_LIST;
    }

}
