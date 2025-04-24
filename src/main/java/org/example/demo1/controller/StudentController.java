package org.example.demo1.controller;


import jakarta.validation.Valid;
import org.example.demo1.dto.CommonResponse;
import org.example.demo1.dto.StudentDto;
import org.example.demo1.service.StudentService;
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


import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<CommonResponse<Long>> addStudent(@Valid @RequestBody StudentDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CommonResponse<>(
                        this.studentService.addStudent(request), true, List.of("CREATED"), 200));
    }

    @PutMapping
    public ResponseEntity<CommonResponse<?>> editStudent(@Valid @RequestBody StudentDto request) {
        this.studentService.editStudent(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(null, true, List.of("Ok"), 200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<?>> deleteStudent(@PathVariable("id") Long id) {
        this.studentService.deleteStudent(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(null, true, List.of("Ok"), 200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<StudentDto>> getStudentById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(
                        this.studentService.getStudentById(id), true, List.of("Ok"), 200));
    }

    @GetMapping()
    public ResponseEntity<CommonResponse<List<StudentDto>>> getStudentsByGroupId(@RequestParam("groupId") Long groupId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(
                        this.studentService.getByGroupId(groupId), true, List.of("Ok"), 200));
    }
}