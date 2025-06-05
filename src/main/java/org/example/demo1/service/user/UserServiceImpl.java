package org.example.demo1.service.user;

import org.example.demo1.dto.user.UserDto;
import org.example.demo1.entity.user.Role;
import org.example.demo1.entity.user.UserEntity;
import org.example.demo1.entity.user.UserProfileReference;
import org.example.demo1.exception.AlreadyExistException;
import org.example.demo1.mapper.UserMapper;
import org.example.demo1.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public void createUser(String email, String password, Role role, UserProfileReference profile) {
        if (userRepository.findByUsername(email).isPresent()) {
            throw new AlreadyExistException("USER", email);
        }

        UserEntity user = new UserEntity();
        user.setUsername(email);
        user.setPassword(this.passwordEncoder.encode(password));
        user.setRole(role);
        user.setProfileId(profile.getId());

        this.userRepository.save(user);
    }

    @Override
    public void deleteUserByProfile(Role role, UserProfileReference profile) {
        this.userRepository.findByRoleAndProfileId(role, profile.getId())
                .ifPresent(userRepository::delete);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return this.userMapper.mapToDtos((List<UserEntity>) this.userRepository.findAll());
    }
}