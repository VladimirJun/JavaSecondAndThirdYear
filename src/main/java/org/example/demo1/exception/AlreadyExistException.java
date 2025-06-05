package org.example.demo1.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistException extends CommonException {
    public AlreadyExistException(String entity, Object email) {
        super(entity + " with email: " + email + " already exist", HttpStatus.NOT_FOUND);
    }
}