package org.example.demo1.exception.handle;

import java.util.List;

public record ExceptionResponse(int code, List<String> messages, String cause) {
}