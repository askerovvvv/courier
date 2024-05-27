package com.example.courier.models.mapper;

import com.example.courier.models.dto.AddressDto;
import com.example.courier.models.dto.ClientDto;
import com.example.courier.models.entity.Address;
import com.example.courier.models.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component("spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    default Address toAddress(AddressDto addressDto) {
        return Address.builder()
                .city(addressDto.city())
                .floor(addressDto.floor())
                .flatNumber(addressDto.flatNumber())
                .street(addressDto.street())
                .houseNumber(addressDto.houseNumber())
                .build();
    }

    default AddressDto toAddressDto(Address address) {
            return new AddressDto(
                    address.getCity(),
                    address.getStreet(),
                    address.getHouseNumber(),
                    address.getFlatNumber(),
                    address.getFloor()
            );
    }

}
