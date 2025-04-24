package org.example.demo1.controller;

import org.example.demo1.dto.CommonResponse;
import org.example.demo1.dto.SubjectDto;
import org.example.demo1.service.SubjectService;
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
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<CommonResponse<Long>> addSubject(@RequestBody SubjectDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CommonResponse<>(
                        this.subjectService.addSubject(request), true, List.of("CREATED"), 200));
    }

    @PutMapping
    public ResponseEntity<CommonResponse<?>> editSubject(@RequestBody SubjectDto request) {
        this.subjectService.editSubject(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(null, true, List.of("Ok"), 200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<?>> deleteSubject(@PathVariable("id") Long id) {
        this.subjectService.deleteSubject(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(null, true, List.of("Ok"), 200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<SubjectDto>> getSubjectById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(
                        this.subjectService.getSubjectById(id), true, List.of("Ok"), 200));
    }
}