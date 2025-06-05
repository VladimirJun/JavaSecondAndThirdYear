package org.example.demo1.service.user;



import org.example.demo1.dto.user.UserDto;
import org.example.demo1.entity.user.Role;
import org.example.demo1.entity.user.UserProfileReference;

import java.util.List;

public interface UserService {
    void createUser(String email, String password, Role role, UserProfileReference profile);

    void deleteUserByProfile(Role role, UserProfileReference profile);

    List<UserDto> getAllUsers();
}