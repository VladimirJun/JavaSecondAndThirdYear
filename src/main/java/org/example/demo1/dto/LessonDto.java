package org.example.demo1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.Map;

public record LessonDto(
        Long id,
        @NotNull Long teacherId,
        @NotNull Long groupId,
        @JsonFormat(pattern = "yyyy.MM.dd") LocalDate date,
        @Positive int numberOfLesson,
        Map<Long, Boolean> attendance) {
}