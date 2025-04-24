package org.example.demo1.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class CommonException extends RuntimeException {
    private HttpStatus status;

    protected CommonException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(String message) {
        super(message);
    }

}