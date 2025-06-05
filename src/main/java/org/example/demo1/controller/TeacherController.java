package org.example.demo1.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.example.demo1.dto.common.CommonResponse;
import org.example.demo1.dto.teacher.CreateTeacherDto;
import org.example.demo1.dto.teacher.TeacherDto;
import org.example.demo1.dto.teacher.UpdateTeacherDto;
import org.example.demo1.service.TeacherService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@Validated
@Tag(
        name = "Контроллер для преподавателя.",
        description = "Позволяет выполнять все действия для преподавателя."
)
@SecurityRequirement(name = "JWT")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<Long>> addTeacher(
            @Valid @RequestBody CreateTeacherDto createTeacherDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CommonResponse<>(
                        this.teacherService.addTeacher(createTeacherDto), true, List.of("CREATED"), 200));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<?>> editTeacher(
            @Min(1) @PathVariable Long id, @Valid @RequestBody UpdateTeacherDto updateTeacherDto
    ) {
        this.teacherService.editTeacher(id, updateTeacherDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(null, true, List.of("Ok"), 200));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<?>> deleteTeacher(
            @Min(1) @PathVariable("id") Long id
    ) {
        this.teacherService.deleteTeacher(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(null, true, List.of("Ok"), 200));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<TeacherDto>> getTeacherFromAdmin(
            @Min(1) @PathVariable("id") Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(
                        this.teacherService.getTeacherById(id), true, List.of("Ok"), 200));
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<CommonResponse<TeacherDto>> getTeacher(
            @AuthenticationPrincipal UserDetailsImpl teacher
    ) {
        Long id = teacher.getProfileId();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(
                        this.teacherService.getTeacherById(id), true, List.of("Ok"), 200));
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<List<TeacherDto>>> getTeachers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(
                        this.teacherService.getTeachers(), true, List.of("Ok"), 200));
    }
}