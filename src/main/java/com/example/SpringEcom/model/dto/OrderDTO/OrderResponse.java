package com.example.SpringEcom.model.dto.OrderDTO;

import java.time.LocalDate;
import java.util.List;

import com.example.SpringEcom.model.OrderStatus;

public record OrderResponse(
    String orderId,
    String customerName,
    String email,
    OrderStatus status,
    LocalDate orderDate,
    List<OrderItemResponse> items
) {}
