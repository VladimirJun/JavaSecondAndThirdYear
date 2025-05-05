package org.example.demo1.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Map<String, String> users = Map.of(
            "admin", "$2a$10$7Q9J1E1F1E1F1E1F1E1F1E1F1E1F1E1F1E1F1E1F1E1F1E1F1E", // пароль: admin
            "user", "$2a$10$7Q9J1E1F1E1F1E1F1E1F1E1F1E1F1E1F1E1F1E1F1E1F1E1F1E"  // пароль: user
    );

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!users.containsKey(username)) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.builder()
                .username(username)
                .password(users.get(username))
                .roles("USER")
                .build();
    }
}