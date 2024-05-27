package com.example.courier.models.mapper;

import com.example.courier.models.dto.AddressDto;
import com.example.courier.models.dto.ParcelDto;
import com.example.courier.models.dto.ParcelSizeDto;
import com.example.courier.models.entity.Address;
import com.example.courier.models.entity.Parcel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component("spring")
public interface ParcelMapper {

    ParcelMapper INSTANCE = Mappers.getMapper(ParcelMapper.class);

    default Parcel toParcel(ParcelDto parcelDto) {
        return Parcel.builder()
                .width(parcelDto.parcelSize().width())
                .length(parcelDto.parcelSize().length())
                .height(parcelDto.parcelSize().height())
                .weight(parcelDto.parcelSize().weight())
                .name(parcelDto.name())
                .build();
    }

    default ParcelDto toParcelDto(Parcel parcel) {
        return new ParcelDto(
                parcel.getDateToDelivery(),
                parcel.getName(),
                new ParcelSizeDto(
                        parcel.getLength(),
                        parcel.getWidth(),
                        parcel.getHeight(),
                        parcel.getWeight()
                )
        );
    }

}
