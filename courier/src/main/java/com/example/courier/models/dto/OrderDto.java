package com.example.courier.models.dto;

public record OrderDto(
        ClientDto sender,
        AddressDto pickUpPoint,

        ClientDto recipient,
        AddressDto deliveryPoint,

        ParcelDto parcel
) {
}
