package com.example.courier.service.impl;

import com.example.courier.exceptions.CustomValidationException;
import com.example.courier.models.dto.CustomValidationErrorDto;
import com.example.courier.models.dto.ParcelDto;
import com.example.courier.models.entity.Parcel;
import com.example.courier.models.mapper.ParcelMapper;
import com.example.courier.repository.ParcelRepository;
import com.example.courier.service.ParcelService;
import com.example.courier.validator.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ParcelServiceImpl implements ParcelService {

    private final ParcelRepository parcelRepository;
    private final CustomValidator<ParcelDto> parcelValidator;

    @Override
    public Parcel saveParcel(ParcelDto parcelDto) {
        List<CustomValidationErrorDto> fieldErrors = parcelValidator.validate(parcelDto);
        if (!fieldErrors.isEmpty()) throw new CustomValidationException("Validation error", fieldErrors);

        return parcelRepository.save(ParcelMapper.INSTANCE.toParcel(parcelDto));
    }
}
