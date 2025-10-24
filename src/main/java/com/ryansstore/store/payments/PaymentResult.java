package com.ryansstore.store.payments;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentResult {
    private Long orderId;
    private PaymentStatus status;
}


