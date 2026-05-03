package com.example.SpringEcom.model.dto.OrderDTO;

import com.example.SpringEcom.model.OrderStatus;

import lombok.Data;

@Data
public class OrderStatusRequest {
    private OrderStatus status;
}
