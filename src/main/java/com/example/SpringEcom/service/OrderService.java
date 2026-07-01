package com.example.SpringEcom.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SpringEcom.model.Order;
import com.example.SpringEcom.model.OrderItem;
import com.example.SpringEcom.model.OrderStatus;
import com.example.SpringEcom.model.Product;
import com.example.SpringEcom.model.dto.OrderDTO.OrderItemRequest;
import com.example.SpringEcom.model.dto.OrderDTO.OrderItemResponse;
import com.example.SpringEcom.model.dto.OrderDTO.OrderRequest;
import com.example.SpringEcom.model.dto.OrderDTO.OrderResponse;
import com.example.SpringEcom.repo.OrderRepo;
import com.example.SpringEcom.repo.ProductRepo;

@Service
public class OrderService {

    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    public OrderService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    @Transactional
    public OrderResponse placeOrder(OrderRequest request) {

        Order order = new Order();

        String orderId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        order.setOrderId(orderId);
        order.setCustomerName(request.customerName());
        order.setEmail(request.email());
        order.setStatus(OrderStatus.PLACED);
        order.setOrderDate(LocalDate.now());

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequest itemReq : request.items()) {

            Product product = productRepo.findById(itemReq.productId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStockQuantity() < itemReq.quantity()) {
                throw new RuntimeException("Insufficient stock");
            }

            product.setStockQuantity(product.getStockQuantity() - itemReq.quantity());
            productRepo.save(product);

            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(itemReq.quantity())
                    .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(itemReq.quantity())))
                    .order(order)
                    .build();

            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);

        Order savedOrder = orderRepo.save(order);

        return mapToOrderResponse(savedOrder);
    }

    public List<OrderResponse> getAllOrderResponses() {

        List<Order> orders = orderRepo.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {
            orderResponses.add(mapToOrderResponse(order));
        }

        return orderResponses;
    }

    public Order updateOrderStatus(String orderId, OrderStatus status) {

        Order order = orderRepo.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);

        return orderRepo.save(order);
    }

    private OrderResponse mapToOrderResponse(Order order) {

        List<OrderItemResponse> itemResponses = new ArrayList<>();

        for (OrderItem item : order.getOrderItems()) {
            itemResponses.add(mapToOrderItemResponse(item));
        }

        return new OrderResponse(
                order.getOrderId(),
                order.getCustomerName(),
                order.getEmail(),
                order.getStatus(),
                order.getOrderDate(),
                itemResponses);
    }

    private OrderItemResponse mapToOrderItemResponse(OrderItem item) {

        return new OrderItemResponse(
                item.getProduct().getName(),
                item.getQuantity(),
                item.getTotalPrice());
    }
}