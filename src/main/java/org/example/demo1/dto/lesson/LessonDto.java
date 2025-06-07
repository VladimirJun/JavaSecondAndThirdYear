package org.example.demo1.dto.lesson;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Map;

@Schema(description = "DTO для создания пары")
public record LessonDto(
        @Schema(accessMode = Schema.AccessMode.READ_ONLY) Long id,

        @Schema(description = "Id преподавателя", defaultValue = "1")
        @NotNull Long teacherId,

        @Schema(description = "Id группы", defaultValue = "1")
        @NotNull Long groupId,

        @Schema(description = "Дата проведения", defaultValue = "2025.06.06")
        @NotNull @JsonFormat(pattern = "yyyy.MM.dd") LocalDate date,

        @Schema(description = "Номер пары", defaultValue = "1")
        @Min(1) @Max(10) int numberOfLesson,

        @Schema(description = "Посещаемость")
        Map<Long, Boolean> attendance
) {
}