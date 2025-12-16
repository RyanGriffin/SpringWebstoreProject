package com.ryansstore.store.payments;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CheckoutRequest {
    @NotNull(message = "cart id is required!")
    private UUID cartId;
}
