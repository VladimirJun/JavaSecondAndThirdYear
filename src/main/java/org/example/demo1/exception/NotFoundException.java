package org.example.demo1.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CommonException {
    public NotFoundException(String entity, Object id) {
        super(entity + " with id: " + id + " not found", HttpStatus.NOT_FOUND);
    }
}