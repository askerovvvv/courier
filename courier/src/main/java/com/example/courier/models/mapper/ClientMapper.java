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
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    default Client toClient(ClientDto clientDto) {
        return Client.builder()
                .firstname(clientDto.firstname())
                .lastname(clientDto.lastname())
                .patronomic(clientDto.patronomyc())
                .phoneNumber(clientDto.phoneNumber())
                .build();
    }

    default ClientDto toClientDto(Client client) {
        return new ClientDto(
                client.getFirstname(),
                client.getLastname(),
                client.getPatronomic(),
                client.getPhoneNumber()
        );
    }

}
