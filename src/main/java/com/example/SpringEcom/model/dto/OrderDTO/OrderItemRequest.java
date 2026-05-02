package com.example.SpringEcom.model.dto.OrderDTO;

public record OrderItemRequest(
    int productId,
    int quantity
) {}
