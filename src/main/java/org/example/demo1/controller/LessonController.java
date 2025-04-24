package org.example.demo1.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import org.example.demo1.dto.CommonResponse;
import org.example.demo1.dto.LessonDto;
import org.example.demo1.service.LessonService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    public ResponseEntity<CommonResponse<Long>> addLesson(@Valid @RequestBody LessonDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CommonResponse<>(
                        this.lessonService.addLesson(request), true, List.of("CREATED"), 200));
    }

    @PutMapping
    public ResponseEntity<CommonResponse<?>> editLesson(@Valid @RequestBody LessonDto request) {
        this.lessonService.editLesson(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(null, true, List.of("Ok"), 200));
    }

    @DeleteMapping("/group/{id}")
    public ResponseEntity<CommonResponse<?>> deleteLessonByGroupId(@PathVariable("id") Long id) {
        this.lessonService.deleteLessonByGroupId(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(null, true, List.of("Ok"), 200));
    }

    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<CommonResponse<?>> deleteLessonByTeacherId(@PathVariable("id") Long id) {
        this.lessonService.deleteLessonByTeacherId(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(null, true, List.of("Ok"), 200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<LessonDto>> getLessonById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(
                        this.lessonService.getLessonById(id), true, List.of("Ok"), 200));
    }

    @GetMapping("/teacher/{id}")
    public ResponseEntity<CommonResponse<List<LessonDto>>> getLessonByTeacherForPeriod(
            @PathVariable("id") Long id,
            @RequestParam("dateStart") LocalDate dateStart,
            @RequestParam("dateEnd") LocalDate dateEnd
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(
                        this.lessonService.getLessonByTeacherForPeriod(id, dateStart, dateEnd),
                        true,
                        List.of("Ok"),
                        200)
                );
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<CommonResponse<List<LessonDto>>> getLessonByGroupForPeriod(
            @PathVariable("id") Long id,
            @RequestParam("dateStart")  LocalDate dateStart,
            @RequestParam("dateEnd")  LocalDate dateEnd
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(
                        this.lessonService.getLessonByGroupForPeriod(id, dateStart, dateEnd),
                        true,
                        List.of("Ok"),
                        200)
                );
    }
}