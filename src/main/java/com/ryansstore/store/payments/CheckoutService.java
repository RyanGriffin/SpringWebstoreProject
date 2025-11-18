package com.ryansstore.store.payments;

import com.ryansstore.store.orders.OrderStatus;
import com.ryansstore.store.authentication.AuthService;
import com.ryansstore.store.carts.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ryansstore.store.carts.Cart;
import com.ryansstore.store.orders.Order;
import com.ryansstore.store.carts.CartRepository;
import com.ryansstore.store.orders.OrderRepository;
import com.ryansstore.store.carts.CartNotFoundException;
import com.ryansstore.store.carts.EmptyCartException;
import lombok.RequiredArgsConstructor;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CheckoutService {
    private final AuthService authService;
    private final CartService cartService;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final PaymentGateway paymentGateway;

    @Transactional
    public CheckoutResponse checkout(CheckoutRequest request) {
        UUID cartId = request.getCartId();
        Cart cart = cartRepository.getCartWithItems(cartId).orElse(null);

        if(cart == null)
            throw new CartNotFoundException();
        if(cart.isEmpty())
            throw new EmptyCartException();

        Order order = Order.fromCart(cart, authService.getCurrentUser());

        orderRepository.save(order);

        try {
            CheckoutSession session = paymentGateway.createCheckoutSession(order);

            cartService.clearCart(cartId);

            return new CheckoutResponse(order.getId(), session.getSessionId(), session.getCheckoutUrl());
        }
        catch(PaymentException ex) {
            orderRepository.delete(order); // delete because client could attempt multiple times & create order with no meaning in our application
            throw ex;
        }
    }

    public void handleWebhookEvent(WebhookRequest request) {
        paymentGateway
                .parseWebhookRequest(request)
                .ifPresent(paymentResult -> {
                    Order order = orderRepository.findById(paymentResult.getOrderId()).orElseThrow();
                    switch (paymentResult.getStatus()) {
                        case PAID -> order.setStatus(OrderStatus.PAID);
                        case PENDING -> order.setStatus(OrderStatus.PENDING);
                        case CANCELED -> order.setStatus(OrderStatus.CANCELED);
                        case FAILED ->  order.setStatus(OrderStatus.FAILED);
                    }
                    orderRepository.save(order);
                });
    }
}
