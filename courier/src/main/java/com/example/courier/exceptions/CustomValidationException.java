package com.example.courier.exceptions;

import com.example.courier.models.dto.CustomValidationErrorDto;
import lombok.Getter;

import java.util.List;

@Getter
public class CustomValidationException extends RuntimeException {

    private final List<CustomValidationErrorDto> fieldsError;

    public CustomValidationException(String message, List<CustomValidationErrorDto> fieldsError) {
        super(message);
        this.fieldsError = fieldsError;
    }
}
