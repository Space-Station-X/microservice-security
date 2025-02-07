package com.practice.auth.RequestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema
public record AuthLoginRequestDto(
        @NotBlank(message = "El email es obligatorio")
        @Schema(description = "Correo del usuario", required = true, example = "admin@example.com")
        String email,
        @NotBlank(message = "La contraseña es obligatoria")
        @Schema(description = "Contraseña del usuario", required = true, example = "admin123")
        String password) {
}
