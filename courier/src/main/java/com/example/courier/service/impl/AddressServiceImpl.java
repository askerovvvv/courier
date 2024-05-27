package com.example.courier.service.impl;

import com.example.courier.exceptions.CustomValidationException;
import com.example.courier.models.dto.AddressDto;
import com.example.courier.models.dto.CustomValidationErrorDto;
import com.example.courier.models.entity.Address;
import com.example.courier.models.mapper.AddressMapper;
import com.example.courier.repository.AddressRepository;
import com.example.courier.service.AddressService;
import com.example.courier.validator.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CustomValidator<AddressDto> addressValidator;

    @Override
    public Address saveAddress(AddressDto addressDto) {
        List<CustomValidationErrorDto> fieldErrors = addressValidator.validate(addressDto);
        if (!fieldErrors.isEmpty()) throw new CustomValidationException("Validation error", fieldErrors);

        return addressRepository.save(AddressMapper.INSTANCE.toAddress(addressDto));
    }
}
