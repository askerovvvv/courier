package com.example.courier.service;

import com.example.courier.models.entity.Address;
import com.example.courier.models.entity.Client;

public interface ClientAddressService {
    void saveClientAddress(Client sender, Address pickUpPoint, Address deliveryPoint);
}
