package com.example.courier.repository;

import com.example.courier.models.entity.Order;
import com.example.courier.models.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByOrderStatus(OrderStatus orderStatus);
}
