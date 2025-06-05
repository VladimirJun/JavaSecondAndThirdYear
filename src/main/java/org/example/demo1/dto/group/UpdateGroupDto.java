package org.example.demo1.dto.group;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Обновление группы")
public record UpdateGroupDto(
        @Schema(description = "Название группы", defaultValue = "ММБ-203")
        @NotBlank @Size(min = 2, max = 15) String title
) {
}