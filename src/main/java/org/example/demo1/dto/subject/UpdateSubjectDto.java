package org.example.demo1.dto.subject;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Обновление предмета")
public record UpdateSubjectDto(
        @Schema(description = "Название предмета", defaultValue = "Серверные приложения")
        @NotBlank @Size(min = 2, max = 35) String title
) {
}