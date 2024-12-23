package org.example.validator;

import java.util.List;

public interface Validator<T> {
    List<String> getErrors(T entity);
}