package org.example.demo1.controller;

import jakarta.validation.Valid;
import org.example.demo1.dto.CommonResponse;
import org.example.demo1.dto.GroupDto;
import org.example.demo1.service.GroupService;
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
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<CommonResponse<Long>> addGroup(@Valid @RequestBody GroupDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CommonResponse<>(
                        this.groupService.addGroup(request), true, List.of("CREATED"), 200));
    }

    @PutMapping
    public ResponseEntity<CommonResponse<?>> editGroup(@Valid @RequestBody GroupDto request) {
        this.groupService.editGroup(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(null, true, List.of("Ok"), 200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<?>> deleteGroup(@PathVariable Long id) {
        this.groupService.deleteGroup(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(null, true, List.of("Ok"), 200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<GroupDto>> getGroupById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(
                        this.groupService.getGroupById(id), true, List.of("Ok"), 200));
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<GroupDto>>> getGroups() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(
                        this.groupService.getGroups(), true, List.of("Ok"), 200));
    }
}