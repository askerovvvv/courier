package com.example.courier.service.impl;

import com.example.courier.exceptions.BadRequestException;
import com.example.courier.exceptions.CustomValidationException;
import com.example.courier.exceptions.ObjectNotFoundException;
import com.example.courier.models.dto.OrderDto;
import com.example.courier.models.dto.CustomValidationErrorDto;
import com.example.courier.models.dto.ParcelDto;
import com.example.courier.models.entity.*;
import com.example.courier.models.mapper.OrderMapper;
import com.example.courier.repository.OrderRepository;
import com.example.courier.service.*;
import com.example.courier.validator.CustomValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AddressService addressService;
    private final ClientService clientService;
    private final ParcelService parcelService;
    private final ClientAddressService clientAddressService;
    private final CustomValidator<OrderDto> orderValidator;

    @Override
    @Transactional
    public void createOrder(OrderDto orderDto) {
        List<CustomValidationErrorDto> fieldErrors = orderValidator.validate(orderDto);
        if (!fieldErrors.isEmpty()) throw new CustomValidationException("Validation error", fieldErrors);

        if (orderDto.recipient().phoneNumber().length() != 12 || !orderDto.recipient().phoneNumber().startsWith("996")) {
            throw new BadRequestException("Recipient phone number is incorrect!");
        }

        if (orderDto.sender().phoneNumber().length() != 12 || !orderDto.sender().phoneNumber().startsWith("996")) {
            throw new BadRequestException("Sender phone number is incorrect!");
        }

        Address pickUpPoint = addressService.saveAddress(orderDto.pickUpPoint());
        Address deliveryPoint = addressService.saveAddress(orderDto.deliveryPoint());

        Client sender = clientService.saveClient(orderDto.sender());
        Client recipient = clientService.saveClient(orderDto.recipient());

        double orderPrice = calculatePriceForOrder(orderDto.parcel());
        Parcel parcel = parcelService.saveParcel(orderDto.parcel());

        Order order = Order.builder()
                .orderStatus(OrderStatus.IN_PROCESS)
                .pickUpPoint(pickUpPoint)
                .deliveryPoint(deliveryPoint)
                .sender(sender)
                .recipient(recipient)
                .orderPrice(orderPrice)
                .parcel(parcel)
                .build();

        orderRepository.save(order);
        clientAddressService.saveClientAddress(sender, pickUpPoint, deliveryPoint);
    }

    private double calculatePriceForOrder(ParcelDto parcelDto) {
        if (parcelDto.parcelSize().height() == 0 || parcelDto.parcelSize().width() == 0 || parcelDto.parcelSize().length() == 0 || parcelDto.parcelSize().weight() == 0) {
            throw new BadRequestException("Размер посылки не может быть 0!");
        }

        float widthFromMillimeterToDecimeter = parcelDto.parcelSize().width() / 100;
        float lengthFromMillimeterToDecimeter = parcelDto.parcelSize().length() / 100;
        float heightFromMillimeterToDecimeter = parcelDto.parcelSize().height() / 100;

        double priceForCubicMeter = widthFromMillimeterToDecimeter * lengthFromMillimeterToDecimeter * heightFromMillimeterToDecimeter * 1000;
        double priceForKilogram = parcelDto.parcelSize().weight() / 1000 * 100;

        return Math.max(priceForKilogram, priceForCubicMeter);
    }

    @Override
    public OrderStatus findByIdAndReturnStatus(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Order not found!"));

        return order.getOrderStatus();
    }

    @Override
    public List<OrderDto> findOrderWithStatus(OrderStatus orderStatus) {
        return orderRepository.findAllByOrderStatus(orderStatus).stream()
                .map(OrderMapper.INSTANCE::toOrderDto)
                .toList();
    }

    @Override
    public List<OrderDto> findAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderMapper.INSTANCE::toOrderDto)
                .toList();
    }

    @Override
    public OrderStatus changeOrderStatus(Long id, OrderStatus newStatus) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Order not found!"));

        order.setOrderStatus(newStatus);
        orderRepository.save(order);
        return newStatus;
    }

}
