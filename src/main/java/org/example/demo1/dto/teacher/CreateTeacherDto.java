package org.example.demo1.dto.teacher;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Создание преподавателя")
public record CreateTeacherDto(
        @Schema(accessMode = Schema.AccessMode.READ_ONLY) Long id,

        @Schema(description = "Логин учителя", defaultValue = "IVAshaev@omsu.com")
        @NotBlank @Email String email,

        @Schema(description = "Пароль учителя", defaultValue = "teacher1")
        @NotBlank String password,

        @Schema(description = "Имя учителя", defaultValue = "Учитель1")
        @NotBlank @Size(min = 2, max = 15) String name,

        @Schema(description = "Фамилия учителя", defaultValue = "Учитель1")
        @NotBlank @Size(min = 2, max = 25) String surname,

        @Schema(description = "Отчество учителя", defaultValue = "Учитель1")
        @Size(max = 40) String patronymic
) {
}