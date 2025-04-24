package org.example.demo1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.demo1.controller.TeacherController;
import org.example.demo1.dto.TeacherDto;
import org.example.demo1.service.TeacherService;
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

@WebMvcTest(TeacherController.class)
class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addTeacher() throws Exception {
        TeacherDto teacherDto = new TeacherDto(1L, "John", "Doe", "Smith");
        Mockito.when(teacherService.addTeacher(any(TeacherDto.class))).thenReturn(1L);

        mockMvc.perform(post("/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teacherDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data").value(1L));
    }

    @Test
    void editTeacher() throws Exception {
        TeacherDto teacherDto = new TeacherDto(1L, "John", "Doe", "Smith");

        mockMvc.perform(put("/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teacherDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTeacher() throws Exception {
        mockMvc.perform(delete("/teachers/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void getTeacherById() throws Exception {
        TeacherDto teacherDto = new TeacherDto(1L, "John", "Doe", "Smith");
        Mockito.when(teacherService.getTeacherById(anyLong())).thenReturn(teacherDto);

        mockMvc.perform(get("/teachers/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.name").value("John Doe"))
                .andExpect(jsonPath("$.data.subject").value("Math"));
    }

    @Test
    void getTeachers() throws Exception {
        List<TeacherDto> teachers = List.of(
                new TeacherDto(1L, "John", "Doe", "Smith"),
                new TeacherDto(2L, "Jane", "Doe", "Smith")
        );
        Mockito.when(teacherService.getTeachers()).thenReturn(teachers);

        mockMvc.perform(get("/teachers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1L))
                .andExpect(jsonPath("$.data[0].name").value("John"))
                .andExpect(jsonPath("$.data[1].id").value(2L))
                .andExpect(jsonPath("$.data[1].name").value("Jane"));
    }

    @Configuration
    static class TestConfig {
        @Bean
        public TeacherService teacherService() {
            return Mockito.mock(TeacherService.class);
        }
    }
}