package com.ryansstore.store.carts.dtos;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class CartAddItemRequest {
    @NotNull
    private Long productId;
}
