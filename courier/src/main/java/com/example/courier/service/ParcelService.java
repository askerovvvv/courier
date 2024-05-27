package com.example.courier.service;

import com.example.courier.models.dto.ParcelDto;
import com.example.courier.models.entity.Parcel;

public interface ParcelService {
    Parcel saveParcel(ParcelDto parcelDto);
}
