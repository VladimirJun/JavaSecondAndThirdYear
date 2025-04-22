package org.example.demo1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SubjectDto(
        Long id,
        @NotBlank @Size(min = 4, max = 1000) String title) {
}