package com.ryansstore.store.payments;

import lombok.Data;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Data
public class CheckoutResponse {
    private Long orderId;
    private String sessionId;
    private String checkoutUrl;
}
