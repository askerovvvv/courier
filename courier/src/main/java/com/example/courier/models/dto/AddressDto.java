package com.example.courier.models.dto;

import jakarta.validation.constraints.NotNull;

public record AddressDto(
        @NotNull(message = "City can not be null!")
        String city,
        @NotNull(message = "Street can not be null!")
        String street,
        String houseNumber,
        String flatNumber,
        String floor
) {
}
