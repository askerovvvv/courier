package com.example.courier.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

public record ParcelDto(
        @JsonFormat(pattern = "dd.MM.yyyy")
        Date dateToDelivery,
        @NotEmpty(message = "Parcel name can not be empty!")
        String name,
        ParcelSizeDto parcelSize
) {
}
