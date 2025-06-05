package org.example.demo1.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.example.demo1.dto.common.CommonResponse;
import org.example.demo1.dto.group.CreateGroupDto;
import org.example.demo1.dto.group.GroupDto;
import org.example.demo1.dto.group.UpdateGroupDto;
import org.example.demo1.service.GroupService;
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
@RequestMapping("/api/groups")
@PreAuthorize("hasRole('ADMIN')")
@Validated
@Tag(
        name = "Контроллер для групп.",
        description = "Позволяет выполнять все действия для группы."
)
@SecurityRequirement(name = "JWT")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<CommonResponse<Long>> addGroup(
            @Valid @RequestBody CreateGroupDto request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CommonResponse<>(
                        this.groupService.addGroup(request), true, List.of("CREATED"), 200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<?>> editGroup(
            @Min(1) @PathVariable Long id, @Valid @RequestBody UpdateGroupDto updateGroupDto
    ) {
        this.groupService.editGroup(id, updateGroupDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(null, true, List.of("Ok"), 200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<?>> deleteGroup(
            @Min(1) @PathVariable Long id
    ) {
        this.groupService.deleteGroup(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CommonResponse<>(null, true, List.of("Ok"), 200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<GroupDto>> getGroup(
            @Min(1) @PathVariable Long id
    ) {
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