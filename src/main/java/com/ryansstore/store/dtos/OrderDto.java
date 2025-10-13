package com.ryansstore.store.dtos;

import lombok.Data;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private Long productId;
    private List<OrderItemDto> items = new ArrayList<>();
    private BigDecimal totalPrice = BigDecimal.ZERO;
}
