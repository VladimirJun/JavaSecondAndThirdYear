package org.example.response;

import java.util.Collections;
import java.util.List;

public class CommonResponse<T> {

    private final int httpCode;
    private List<String> errors = Collections.emptyList();
    private T response;

    public CommonResponse(int httpCode) {
        this.httpCode = httpCode;
    }

    public CommonResponse(int httpCode, List<String> errors) {
        this.httpCode = httpCode;
        this.errors = errors;
    }

    public CommonResponse(int httpCode, String error) {
        this.httpCode = httpCode;
        this.errors = List.of(error);
    }

    public CommonResponse(int httpCode, T response) {
        this.httpCode = httpCode;
        this.response = response;
    }

    public T getResponse() {
        return response;
    }

    public List<String> getErrors() {
        return errors;
    }

    public int getHttpCode() {
        return httpCode;
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "httpCode=" + httpCode +
                ", errors=" + errors +
                ", response=" + response +
                '}';
    }
}