package com.practice.auth.ResponseDto;

public record AuthResponseDto (Long id, String email, String message, String token, boolean success) {
}