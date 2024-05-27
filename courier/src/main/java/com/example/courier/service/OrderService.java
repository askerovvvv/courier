package com.example.courier.service;

import com.example.courier.models.dto.OrderDto;
import com.example.courier.models.entity.OrderStatus;

import java.util.List;

public interface OrderService {
    void createOrder(OrderDto orderDto);
    OrderStatus findByIdAndReturnStatus(Long id);
    OrderStatus changeOrderStatus(Long id, OrderStatus newStatus);
    List<OrderDto> findAllOrders();
    List<OrderDto> findOrderWithStatus(OrderStatus orderStatus);

}
