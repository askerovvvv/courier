package com.example.courier.service;

import com.example.courier.models.dto.AddressDto;
import com.example.courier.models.entity.Address;

public interface AddressService {

    Address saveAddress(AddressDto addressDto);

}
