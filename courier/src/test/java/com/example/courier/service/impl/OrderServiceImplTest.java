package com.example.courier.service.impl;

import com.example.courier.models.dto.*;
import com.example.courier.models.entity.Address;
import com.example.courier.models.entity.Client;
import com.example.courier.models.entity.Order;
import com.example.courier.models.entity.Parcel;
import com.example.courier.repository.OrderRepository;
import com.example.courier.service.*;
import com.example.courier.validator.CustomValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private AddressService addressService;
    @Mock
    private ClientService clientService;
    @Mock
    private ParcelService parcelService;
    @Mock
    private ClientAddressService clientAddressService;
    @Mock
    private CustomValidator<OrderDto> orderValidator;

    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        orderService = new OrderServiceImpl(
                orderRepository,
                addressService,
                clientService,
                parcelService,
                clientAddressService,
                orderValidator
        );
    }

    @Test
    void testCreateOrder_Success() {
        // Given
        OrderDto orderDto = getOrderDto();
        Address address = new Address();
        Client client = new Client();
        Parcel parcel = new Parcel();

        // Mock actions
        when(orderValidator.validate(orderDto)).thenReturn(new ArrayList<>());

        when(addressService.saveAddress(orderDto.pickUpPoint())).thenReturn(address);
        when(addressService.saveAddress(orderDto.deliveryPoint())).thenReturn(address);

        when(clientService.saveClient(orderDto.sender())).thenReturn(client);
        when(clientService.saveClient(orderDto.recipient())).thenReturn(client);

        when(parcelService.saveParcel(orderDto.parcel())).thenReturn(parcel);
//        when(clientAddressService.saveClientAddress(client, address, address)).then

        // When
        orderService.createOrder(orderDto);

        // Then
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void findByIdAndReturnStatus() {
    }

    @Test
    void changeOrderStatus() {
    }

    @Test
    void findAllOrders() {
    }

    @Test
    void findOrderWithStatus() {
    }

    private AddressDto getPickUpPoint() {
        return new AddressDto(
                "Bishkek",
                "12 mkr",
                "234",
                "12/2",
                "3"
        );
    }

    private AddressDto getDeliveryPoint() {
        return new AddressDto(
                "Osh",
                "kadamja",
                "213123",
                "123/2",
                "4"
        );
    }

    private ClientDto getRecipient() {
        return new ClientDto(
                "Carl",
                "Snow",
                "pat",
                "996777112233"
        );
    }

    private ClientDto getSender() {
        return new ClientDto(
                "John",
                "Smith",
                "pat",
                "996555667788"
        );
    }

    private ParcelDto getParcelDto() {
        return new ParcelDto(
                new Date(),
                "test",
                new ParcelSizeDto(
                        600,
                        400,
                        200,
                        5000
                )
        );
    }

    private OrderDto getOrderDto() {
        return new OrderDto(
                getSender(),
                getPickUpPoint(),
                getRecipient(),
                getDeliveryPoint(),
                getParcelDto()
        );

    }
}