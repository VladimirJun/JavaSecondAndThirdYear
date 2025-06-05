package org.example.demo1.dto.user;


import org.example.demo1.entity.user.Role;

public record UserDto(String username, Role role) {
}