package org.example.demo1.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.example.demo1.dto.common.CommonResponse;
import org.example.demo1.dto.subject.SubjectDto;
import org.example.demo1.dto.subject.UpdateSubjectDto;
import org.example.demo1.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/subjects")
@PreAuthorize("hasRole('ADMIN')")
@Validated
@Tag(
        name = "Контроллер для предметов.",
        description = "Позволяет выполнять все действия для предмета"
)
@SecurityRequirement(name = "JWT")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<CommonResponse<Long>> addSubject(
            @Valid @RequestBody SubjectDto request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CommonResponse<>(
                        this.subjectService.addSubject(request), true, List.of("CREATED"), 200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<?>> editSubject(
            @Min(1) @PathVariable Long id, @Valid @RequestBody UpdateSubjectDto updateSubjectDto
    ) {
        this.subjectService.editSubject(id, updateSubjectDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(null, true, List.of("Ok"), 200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<?>> deleteSubject(
            @Min(1) @PathVariable("id") Long id
    ) {
        this.subjectService.deleteSubject(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(null, true, List.of("Ok"), 200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<SubjectDto>> getSubject(
            @Min(1) @PathVariable("id") Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(
                        this.subjectService.getSubjectById(id), true, List.of("Ok"), 200));
    }

    @GetMapping()
    public ResponseEntity<CommonResponse<List<SubjectDto>>> getSubjects() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(
                        this.subjectService.getAllSubjects(), true, List.of("Ok"), 200));
    }
}