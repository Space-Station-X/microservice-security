package com.practice.auth.controller;

import com.practice.auth.RequestDto.AuthLoginRequestDto;
import com.practice.auth.ResponseDto.AuthResponseDto;
import com.practice.user.service.UserDetailsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "Authentication API")
@Validated
@RequiredArgsConstructor
public class AuthController {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Operation(summary = "Iniciar sesión", description = "Inicia sesión y obtiene un token de autenticación.")
    @ApiResponse(responseCode = "200", description = "Autenticación exitosa")
    @ApiResponse(responseCode = "401", description = "Credenciales incorrectas")
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid AuthLoginRequestDto authDto) {
        AuthResponseDto response = this.userDetailsServiceImpl.loginUser(authDto);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + response.token())
                .body(response);
    }
}
