package com.ryansstore.store.dtos;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class CartItemQuantityRequest {
    @Min(value = 1, message = "quantity must be at least 1!")
    @Max(value = 100, message = "quantity must be at most 100!")
    @NotNull(message = "quantity must be provided!")
    private Integer quantity;
}
