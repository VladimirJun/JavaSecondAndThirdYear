package org.example.demo1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record GroupDto(
        Long id,
        @NotBlank @Size(min = 4, max = 1000) String title,
        List<Long> studentsIds)
//#TODO add list<StudentDtoRequest>
{
}