package org.example.demo1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.demo1.controller.LessonController;
import org.example.demo1.dto.LessonDto;
import org.example.demo1.service.LessonService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LessonController.class)
class LessonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addLesson() throws Exception {
        HashMap<Long , Boolean> lessonData = new HashMap<>();

        LessonDto lessonDto = new LessonDto(1L, 1L, 1L, LocalDate.now(), 0, lessonData);

        Mockito.when(lessonService.addLesson(any(LessonDto.class))).thenReturn(1L);

        mockMvc.perform(post("/lessons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lessonDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data").value(1L));
    }

    @Test
    void editLesson() throws Exception {
        HashMap<Long , Boolean> lessonData = new HashMap<>();

        LessonDto lessonDto = new LessonDto(1L, 1L, 1L, LocalDate.now(), 1, lessonData);

        mockMvc.perform(put("/lessons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lessonDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteLessonByGroupId() throws Exception {
        mockMvc.perform(delete("/lessons/group/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void deleteLessonByTeacherId() throws Exception {
        mockMvc.perform(delete("/lessons/teacher/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void getLessonById() throws Exception {
        HashMap<Long , Boolean> lessonData = new HashMap<>();

        LessonDto lessonDto = new LessonDto(1L, 1L, 1L, LocalDate.now(), 1,lessonData);
        Mockito.when(lessonService.getLessonById(anyLong())).thenReturn(lessonDto);

        mockMvc.perform(get("/lessons/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1L));
    }

    @Test
    void getLessonByTeacherForPeriod() throws Exception {
        HashMap<Long , Boolean> lessonData = new HashMap<>();

        List<LessonDto> lessons = List.of(
                new LessonDto(1L, 1L, 1L, LocalDate.now(), 1, lessonData),
                new LessonDto(2L, 1L, 1L, LocalDate.now(), 2, lessonData)
        );
        Mockito.when(lessonService.getLessonByTeacherForPeriod(anyLong(), any(), any())).thenReturn(lessons);

        mockMvc.perform(get("/lessons/teacher/{id}", 1L)
                        .param("dateStart", "2023-01-01")
                        .param("dateEnd", "2023-12-31"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1L))
                .andExpect(jsonPath("$.data[1].id").value(2L));
    }

    @Test
    void getLessonByGroupForPeriod() throws Exception {
        HashMap<Long , Boolean> lessonData = new HashMap<>();

        List<LessonDto> lessons = List.of(
                new LessonDto(1L, 1L, 1L, LocalDate.now(), 1, lessonData),
                new LessonDto(2L, 1L, 1L, LocalDate.now(), 1, lessonData)
        );
        Mockito.when(lessonService.getLessonByGroupForPeriod(anyLong(), any(), any())).thenReturn(lessons);

        mockMvc.perform(get("/lessons/group/{id}", 1L)
                        .param("dateStart", "2023-01-01")
                        .param("dateEnd", "2023-12-31"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1L))
                .andExpect(jsonPath("$.data[1].id").value(2L));
    }

    @Configuration
    static class TestConfig {
        @Bean
        public LessonService lessonService() {
            return Mockito.mock(LessonService.class);
        }
    }
}