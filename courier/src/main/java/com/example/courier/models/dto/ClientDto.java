package com.example.courier.models.dto;

import jakarta.validation.constraints.NotNull;

public record ClientDto(
        @NotNull(message = "Firstname can not be null!")
        String firstname,
        @NotNull(message = "Lastname can not be null!")
        String lastname,
        @NotNull(message = "Patronomyc can not be null!")
        String patronomyc,
        @NotNull(message = "Phone number can not be null!")
        String phoneNumber
) {
}
