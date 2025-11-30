package com.tarkasoftinnovation.assignment.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        String apiPath,
        int statusCode,
        String message,
        LocalDateTime timestamp
) {
}
