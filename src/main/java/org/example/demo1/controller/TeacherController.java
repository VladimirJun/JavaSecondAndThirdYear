package org.example.demo1.controller;

import org.example.demo1.dto.CommonResponse;
import org.example.demo1.dto.TeacherDto;
import org.example.demo1.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping()
    public ResponseEntity<CommonResponse<Long>> addTeacher(@RequestBody TeacherDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CommonResponse<>(
                        this.teacherService.addTeacher(request), true, List.of("CREATED"), 200));
    }

    @PutMapping()
    public ResponseEntity<CommonResponse<?>> editTeacher(@RequestBody TeacherDto request) {
        this.teacherService.editTeacher(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(null, true, List.of("Ok"), 200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<?>> deleteTeacher(@PathVariable("id") Long id) {
        this.teacherService.deleteTeacher(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(null, true, List.of("Ok"), 200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<TeacherDto>> getTeacherById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(
                        this.teacherService.getTeacherById(id), true, List.of("Ok"), 200));
    }

    @GetMapping()
    public ResponseEntity<CommonResponse<List<TeacherDto>>> getTeachers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(
                        this.teacherService.getTeachers(), true, List.of("Ok"), 200));
    }
}