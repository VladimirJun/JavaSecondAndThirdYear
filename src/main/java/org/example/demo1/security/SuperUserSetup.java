package org.example.demo1.security;

import org.example.demo1.entity.user.Role;
import org.example.demo1.entity.user.UserEntity;
import org.example.demo1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SuperUserSetup implements CommandLineRunner {


    public static final String ADMIN_USERNAME = "admin";


    @Value("${app.admin.password:admin123}")
    private String adminPassword;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SuperUserSetup(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        initUser(ADMIN_USERNAME, adminPassword, Role.ROLE_ADMIN);
    }

    private void initUser(String username, String password, Role role) {

        if (this.userRepository.findByUsername(username).isPresent()) {
            return;
        }

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(this.passwordEncoder.encode(password));
        user.setRole(role);

        this.userRepository.save(user);
    }
}