package com.example.SpringEcom.model.dto.OrderDTO;

import java.math.BigDecimal;

public record OrderItemResponse(
    String productName,
    int quantity,
    BigDecimal totalPrice
) {}
