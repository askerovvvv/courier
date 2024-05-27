package com.example.courier.exceptions;

import com.example.courier.models.dto.CustomValidationErrorDto;
import lombok.Getter;

import java.util.List;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
