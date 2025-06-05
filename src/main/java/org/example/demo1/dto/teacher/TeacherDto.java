package org.example.demo1.dto.teacher;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TeacherDto(
        Long id,
        @NotBlank @Size(min = 4, max = 1000) String name,
        @NotBlank @Size(min = 4, max = 1000) String surname,
        String patronymic
) {
}