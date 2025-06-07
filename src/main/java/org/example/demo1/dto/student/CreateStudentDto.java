package org.example.demo1.dto.student;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.example.demo1.entity.StudentStatus;

@Schema(description = "Создание студента")
public record CreateStudentDto(
        @Schema(accessMode = Schema.AccessMode.READ_ONLY) Long id,

        @Schema(description = "Логин", defaultValue = "petrov@omsu.com")
        @NotBlank @Email String email,

        @Schema(description = "Пароль", defaultValue = "vova123")
        @NotBlank String password,

        @Schema(description = "Имя студента", defaultValue = "Владимир")
        @NotBlank @Size(min = 2, max = 15) String name,

        @Schema(description = "Фамилия студента", defaultValue = "Петров")
        @NotBlank @Size(min = 2, max = 25) String surname,

        @Schema(description = "Отчество студента", defaultValue = "Юрьевич")
        @Size(max = 40) String patronymic,

        @Schema(description = "Статус студента", defaultValue = "ACTIVE")
        @NotNull StudentStatus statusOfStudents,

        @Schema(description = "Id группы", defaultValue = "1")
        @NotNull @Positive Long groupId
) {
}