package com.example.courier.models.dto;

import jakarta.validation.constraints.NotEmpty;

public record ParcelSizeDto(
        @NotEmpty(message = "Length can not be null!")
        float length,
        float width,
        float height,

        float weight
) {
}
