package com.example.courier.service.impl;

import com.example.courier.models.entity.Address;
import com.example.courier.models.entity.Client;
import com.example.courier.models.entity.ClientAddress;
import com.example.courier.repository.ClientAddressRepository;
import com.example.courier.service.ClientAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientAddressServiceImpl implements ClientAddressService {

    private final ClientAddressRepository clientAddressRepository;

    @Override
    public void saveClientAddress(Client sender, Address pickUpPoint, Address deliveryPoint) {
        ClientAddress clientAddress = ClientAddress.builder()
                .sender(sender)
                .deliveryPoint(deliveryPoint)
                .pickUpPoint(pickUpPoint)
                .build();

        clientAddressRepository.save(clientAddress);
    }
}
