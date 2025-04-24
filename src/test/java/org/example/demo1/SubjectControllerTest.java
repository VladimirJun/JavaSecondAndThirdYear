package org.example.demo1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.demo1.controller.SubjectController;
import org.example.demo1.dto.SubjectDto;
import org.example.demo1.service.SubjectService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SubjectController.class)
class SubjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addSubject() throws Exception {
        SubjectDto subjectDto = new SubjectDto(1L, "Math");
        Mockito.when(subjectService.addSubject(any(SubjectDto.class))).thenReturn(1L);

        mockMvc.perform(post("/subjects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subjectDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data").value(1L));
    }

    @Test
    void editSubject() throws Exception {
        SubjectDto subjectDto = new SubjectDto(1L, "Math");

        mockMvc.perform(put("/subjects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subjectDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteSubject() throws Exception {
        mockMvc.perform(delete("/subjects/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void getSubjectById() throws Exception {
        SubjectDto subjectDto = new SubjectDto(1L, "Math");
        Mockito.when(subjectService.getSubjectById(anyLong())).thenReturn(subjectDto);

        mockMvc.perform(get("/subjects/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.name").value("Math"));
    }

    @Configuration
    static class TestConfig {
        @Bean
        public SubjectService subjectService() {
            return Mockito.mock(SubjectService.class);
        }
    }
}