package org.example.demo1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.demo1.controller.GroupController;
import org.example.demo1.dto.GroupDto;
import org.example.demo1.service.GroupService;
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

@WebMvcTest(GroupController.class)
class GroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addGroup() throws Exception {
        GroupDto groupDto = new GroupDto(1L, "Group A", List.of(1L, 2L));
        Mockito.when(groupService.addGroup(any(GroupDto.class))).thenReturn(1L);

        mockMvc.perform(post("/groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data").value(1L));
    }

    @Test
    void editGroup() throws Exception {
        GroupDto groupDto = new GroupDto(1L, "Group A", List.of(1L, 2L));

        mockMvc.perform(put("/groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteGroup() throws Exception {
        mockMvc.perform(delete("/groups/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void getGroupById() throws Exception {
        GroupDto groupDto = new GroupDto(1L, "Group A", List.of(1L, 2L));
        Mockito.when(groupService.getGroupById(anyLong())).thenReturn(groupDto);

        mockMvc.perform(get("/groups/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.name").value("Group A"));
    }

    @Test
    void getGroups() throws Exception {
        List<GroupDto> groups = List.of(
                new GroupDto(1L, "Group A", List.of(1L, 2L)),
                new GroupDto(2L, "Group B", List.of(3L, 4L))
        );
        Mockito.when(groupService.getGroups()).thenReturn(groups);

        mockMvc.perform(get("/groups"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1L))
                .andExpect(jsonPath("$.data[1].id").value(2L));
    }

    @Configuration
    static class TestConfig {
        @Bean
        public GroupService groupService() {
            return Mockito.mock(GroupService.class);
        }
    }
}