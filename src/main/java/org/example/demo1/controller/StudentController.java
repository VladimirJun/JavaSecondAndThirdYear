package org.example.demo1.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.example.demo1.dto.common.CommonResponse;
import org.example.demo1.dto.student.CreateStudentDto;
import org.example.demo1.dto.student.StudentDto;
import org.example.demo1.dto.student.UpdateStudentDto;
import org.example.demo1.service.StudentService;
import org.example.demo1.service.user.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/students")
@Validated
@Tag(
        name = "Контроллер для студентов.",
        description = "Позволяет выполнять все действия для студента."
)
@SecurityRequirement(name = "JWT")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<Long>> addStudent(
            @Valid @RequestBody CreateStudentDto createStudentDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CommonResponse<>(
                        this.studentService.addStudent(createStudentDto), true, List.of("CREATED"), 200));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<?>> editStudent(
            @Min(1) @PathVariable Long id, @Valid @RequestBody UpdateStudentDto updateStudentDto
    ) {
        this.studentService.editStudent(id, updateStudentDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(null, true, List.of("Ok"), 200));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<?>> deleteStudent(
            @Min(1) @PathVariable("id") Long id
    ) {
        this.studentService.deleteStudent(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(null, true, List.of("Ok"), 200));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<StudentDto>> getStudentFromAdmin(
            @Min(1) @PathVariable("id") Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(
                        this.studentService.getStudentById(id), true, List.of("Ok"), 200));
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<CommonResponse<StudentDto>> getStudent(
            @AuthenticationPrincipal UserDetailsImpl student
    ) {
        Long id = student.getProfileId();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(
                        this.studentService.getStudentById(id), true, List.of("Ok"), 200));
    }

    @GetMapping("/group/{id}")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<CommonResponse<List<StudentDto>>> getStudentsByGroupId(
            @Min(1) @PathVariable("id") Long groupId
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(
                        this.studentService.getByGroupId(groupId), true, List.of("Ok"), 200));
    }
}