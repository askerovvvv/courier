package com.example.courier.service;

import com.example.courier.models.dto.ClientDto;
import com.example.courier.models.entity.Client;

public interface ClientService {
    Client saveClient(ClientDto clientDto);
}
