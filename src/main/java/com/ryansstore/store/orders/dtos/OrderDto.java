package com.ryansstore.store.orders.dtos;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private Long productId;
    private List<OrderItemDto> items;
    private BigDecimal totalPrice;
}
