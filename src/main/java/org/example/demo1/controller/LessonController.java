package org.example.demo1.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.example.demo1.dto.common.CommonResponse;
import org.example.demo1.dto.lesson.LessonDto;
import org.example.demo1.dto.lesson.UpdateLessonDto;
import org.example.demo1.service.LessonService;
import org.example.demo1.service.user.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@Validated
@Tag(
        name = "Контроллер для пар.",
        description = "Позволяет выполнять все действия для пары."
)
@SecurityRequirement(name = "JWT")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<Long>> addLesson(
            @Valid @RequestBody LessonDto lessonDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CommonResponse<>(
                        this.lessonService.addLesson(lessonDto), true, List.of("CREATED"), 200));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<?>> editLesson(
            @Min(1) @PathVariable Long id, @Valid @RequestBody UpdateLessonDto updateLessonDto
    ) {
        this.lessonService.editLesson(id, updateLessonDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(null, true, List.of("Ok"), 200));
    }

    @DeleteMapping("/group/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<?>> deleteLessonsByGroupId(
            @Min(1) @PathVariable("id") Long id
    ) {
        this.lessonService.deleteLessonsByGroupId(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(null, true, List.of("Ok"), 200));
    }

    @DeleteMapping("/teacher/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<?>> deleteLessonsByTeacherId(
            @Min(1) @PathVariable("id") Long id
    ) {
        this.lessonService.deleteLessonsByTeacherId(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(null, true, List.of("Ok"), 200));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<LessonDto>> getLessonById(
            @Min(1) @PathVariable("id") Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(
                        this.lessonService.getLessonById(id), true, List.of("Ok"), 200));
    }

    @GetMapping("/teacher/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<List<LessonDto>>> getLessonByTeacherForPeriodFromAdmin(
            @Min(1) @PathVariable("id") Long id,
            @RequestParam("dateStart") LocalDate dateStart,
            @RequestParam("dateEnd") LocalDate dateEnd
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(
                        this.lessonService.getLessonsByTeacherForPeriod(id, dateStart, dateEnd),
                        true,
                        List.of("Ok"),
                        200)
                );
    }

    @GetMapping("/teacher/me")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<CommonResponse<List<LessonDto>>> getLessonByTeacherForPeriodFromTeacher(
            @RequestParam("dateStart") LocalDate dateStart,
            @RequestParam("dateEnd") LocalDate dateEnd,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        Long teacherId = userDetails.getProfileId();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(
                        this.lessonService.getLessonsByTeacherForPeriod(teacherId, dateStart, dateEnd),
                        true,
                        List.of("Ok"),
                        200)
                );
    }

    @GetMapping("/group/{id}")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER' ,'ADMIN')")
    public ResponseEntity<CommonResponse<List<LessonDto>>> getLessonByGroupForPeriod(
            @Min(1) @PathVariable("id") Long id,
            @RequestParam("dateStart") LocalDate dateStart,
            @RequestParam("dateEnd") LocalDate dateEnd
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(
                        this.lessonService.getLessonsByGroupForPeriod(id, dateStart, dateEnd),
                        true,
                        List.of("Ok"),
                        200)
                );
    }
}
