package org.example.demo1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.demo1.controller.StudentController;
import org.example.demo1.dto.StudentDto;
import org.example.demo1.entity.StudentStatus;
import org.example.demo1.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addStudent() throws Exception {
        StudentDto studentDto = new StudentDto(1L, "John", "Doe", "Smith", StudentStatus.ACTIVE,1L);
        Mockito.when(studentService.addStudent(any(StudentDto.class))).thenReturn(1L);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data").value(1L));
    }

    @Test
    void editStudent() throws Exception {
        StudentDto studentDto = new StudentDto(1L, "John", "Doe", "Smith", StudentStatus.ACTIVE,1L);

        mockMvc.perform(put("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteStudent() throws Exception {
        mockMvc.perform(delete("/students/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void getStudentById() throws Exception {
        StudentDto studentDto = new StudentDto(1L, "John", "Doe", "Smith", StudentStatus.ACTIVE, 1L);
        Mockito.when(studentService.getStudentById(anyLong())).thenReturn(studentDto);

        mockMvc.perform(get("/students/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.name").value("John"));
    }

    @Test
    void getStudentsByGroupId() throws Exception {
        List<StudentDto> students = List.of(
                new StudentDto(1L, "John", "Doe", "Smith", StudentStatus.ACTIVE,1L),
                new StudentDto(2L, "Jane", "Doe", "Smith", StudentStatus.ACTIVE,1L)
        );
        Mockito.when(studentService.getByGroupId(anyLong())).thenReturn(students);

        mockMvc.perform(get("/students").param("groupId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1L))
                .andExpect(jsonPath("$.data[1].id").value(2L));
    }

    @Configuration
    static class TestConfig {
        @Bean
        public StudentService studentService() {
            return Mockito.mock(StudentService.class);
        }
    }
}