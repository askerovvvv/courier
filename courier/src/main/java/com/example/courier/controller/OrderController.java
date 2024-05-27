package com.example.courier.controller;

import com.example.courier.models.dto.OrderDto;
import com.example.courier.models.entity.OrderStatus;
import com.example.courier.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/all/orders")
    ResponseEntity<?> findAllOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAllOrders());
    }

    @PostMapping("/create-order")
    ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) {
        orderService.createOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/order-status/by/id")
    ResponseEntity<?> findByIdAndReturnOrderStatus(@RequestParam("orderId") Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findByIdAndReturnStatus(orderId));
    }

    @PutMapping("/change/order-status/by/status")
    ResponseEntity<?> findByOrderStatus(@RequestParam("status") OrderStatus status) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findOrderWithStatus(status));
    }

}
