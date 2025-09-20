package com.ryansstore.store.dtos;

import lombok.Data;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;

@Data
public class CartDto {
    private UUID id;
    private List<CartItemDto> items = new ArrayList<>();
    private BigDecimal totalPrice = BigDecimal.ZERO;
}
