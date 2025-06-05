package org.example.demo1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.demo1.dto.security.JwtResponse;
import org.example.demo1.dto.security.LoginDto;
import org.example.demo1.service.auth.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@Tag(
        name = "Контроллер для авторизации.",
        description = "Позволяет авторизоваться студенту или преподавателю."
)
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginDto loginDto) {
        String token = this.authenticationService.login(loginDto.username(), loginDto.password());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new JwtResponse(token));
    }
}