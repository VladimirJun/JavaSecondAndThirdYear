package org.example.demo1.dto.student;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.example.demo1.entity.StudentStatus;

@Schema(description = "Обновление студента")
public record UpdateStudentDto(

        @Schema(description = "Имя студента", defaultValue = "Студент1")
        @NotBlank @Size(min = 2, max = 15) String name,

        @Schema(description = "Фамилия студента", defaultValue = "Студент1")
        @NotBlank @Size(min = 2, max = 25) String surname,

        @Schema(description = "Отчество студента", defaultValue = "Студент1")
        @Size(max = 40) String patronymic,

        @Schema(description = "Статус студента", defaultValue = "ACTIVE")
        @NotNull StudentStatus statusOfStudents,

        @Schema(description = "Id группы", defaultValue = "1")
        @NotNull @Positive Long groupId
) {
}