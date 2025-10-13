package com.ryansstore.store.dtos;

import lombok.Data;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Data
public class CheckoutResponse {
    private Long orderId;
}
