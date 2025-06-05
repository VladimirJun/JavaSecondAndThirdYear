package org.example.demo1.dto.security;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dto для ответа на аутентификацию")
public record JwtResponse(
        String token
) {
}