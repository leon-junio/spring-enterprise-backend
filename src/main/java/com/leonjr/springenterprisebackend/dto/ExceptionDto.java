package com.leonjr.springenterprisebackend.dto;

import org.springframework.http.HttpStatus;

import lombok.NonNull;

public record ExceptionDto(
        @NonNull HttpStatus status,
        String message) {
}