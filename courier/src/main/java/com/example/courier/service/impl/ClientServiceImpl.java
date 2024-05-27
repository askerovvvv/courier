package com.example.courier.service.impl;

import com.example.courier.exceptions.CustomValidationException;
import com.example.courier.models.dto.ClientDto;
import com.example.courier.models.dto.CustomValidationErrorDto;
import com.example.courier.models.entity.Client;
import com.example.courier.models.mapper.ClientMapper;
import com.example.courier.repository.ClientRepository;
import com.example.courier.service.ClientService;
import com.example.courier.validator.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final CustomValidator<ClientDto> clientValidator;

    @Override
    public Client saveClient(ClientDto clientDto) {
        List<CustomValidationErrorDto> fieldErrors = clientValidator.validate(clientDto);
        if (!fieldErrors.isEmpty()) throw new CustomValidationException("Validation error", fieldErrors);

        return clientRepository.save(ClientMapper.INSTANCE.toClient(clientDto));
    }
}
