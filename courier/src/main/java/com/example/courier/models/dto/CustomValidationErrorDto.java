package com.example.courier.models.dto;

public record CustomValidationErrorDto (
        String field,
        String message
) {
}
