package com.ryansstore.store.services;

import com.ryansstore.store.entities.OrderItem;
import org.springframework.stereotype.Service;
import com.ryansstore.store.entities.Cart;
import com.ryansstore.store.entities.Order;
import com.ryansstore.store.entities.OrderStatus;
import com.ryansstore.store.dtos.CheckoutResponse;
import com.ryansstore.store.repositories.CartRepository;
import com.ryansstore.store.repositories.OrderRepository;
import com.ryansstore.store.exceptions.CartNotFoundException;
import com.ryansstore.store.exceptions.EmptyCartException;
import lombok.AllArgsConstructor;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CheckoutService {
    private final CartService cartService;
    private final CartRepository cartRepository;
    private final AuthService authService;
    private final OrderRepository orderRepository;

    public CheckoutResponse checkout(UUID cartId) {
        Cart cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null)
            throw new CartNotFoundException();
        if(cart.getItems().isEmpty())
            throw new EmptyCartException();

        Order order = new Order();
        order.setTotalPrice(cart.getTotalPrice());
        order.setStatus(OrderStatus.PENDING);
        order.setCustomer(authService.getCurrentUser());

        cart.getItems().forEach(item -> {
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(item.getProduct())
                    .quantity(item.getQuantity())
                    .totalPrice(item.getTotalPrice())
                    .unitPrice(item.getProduct().getPrice())
                    .build();
            order.getItems().add(orderItem);
        });

        orderRepository.save(order);

        cartService.clearCart(cartId);

        return new CheckoutResponse(order.getId());

    }
}
