package org.example.demo1.dto.teacher;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Обновление преподавателя")
public record UpdateTeacherDto(
        @Schema(description = "Имя учителя", defaultValue = "Учитель1")
        @NotBlank @Size(min = 2, max = 15) String name,

        @Schema(description = "Фамилия учителя", defaultValue = "Учитель1")
        @NotBlank @Size(min = 2, max = 25) String surname,

        @Schema(description = "Отчество учителя", defaultValue = "Учитель1")
        @Size(max = 40) String patronymic
) {
}