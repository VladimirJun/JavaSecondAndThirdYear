package org.example.demo1.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;
import org.example.demo1.entity.StudentStatus;

public record StudentDto(
        Long id,
        @NotBlank @Size(min = 2, max = 15) String name,
        @NotBlank @Size(min = 2, max = 25) String surname,
        String patronymic,
        StudentStatus studentStatus,
        @NotNull @Positive Long groupId) {
}