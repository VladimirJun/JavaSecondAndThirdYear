package org.example.demo1.exception;

public class ServiceException extends CommonException {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}