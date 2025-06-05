package org.example.demo1.dto.security;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dto для аутентификации")
public record LoginDto(
        @Schema(description = "Username пользователя", defaultValue = "admin")
        @NotBlank String username,

        @Schema(description = "Password пользователя", defaultValue = "admin123")
        @NotBlank String password
) {
}