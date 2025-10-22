package com.ryansstore.store.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;
import com.ryansstore.store.entities.Cart;
import com.ryansstore.store.entities.Order;
import com.ryansstore.store.dtos.CheckoutRequest;
import com.ryansstore.store.dtos.CheckoutResponse;
import com.ryansstore.store.repositories.CartRepository;
import com.ryansstore.store.repositories.OrderRepository;
import com.ryansstore.store.exceptions.CartNotFoundException;
import com.ryansstore.store.exceptions.EmptyCartException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CheckoutService {
    private final AuthService authService;
    private final CartService cartService;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    @Value("${websiteUrl}")
    private String websiteUrl;

    @Transactional
    public CheckoutResponse checkout(CheckoutRequest request) throws StripeException {
        UUID cartId = request.getCartId();
        Cart cart = cartRepository.getCartWithItems(cartId).orElse(null);

        if(cart == null)
            throw new CartNotFoundException();
        if(cart.isEmpty())
            throw new EmptyCartException();

        Order order = Order.fromCart(cart, authService.getCurrentUser());

        orderRepository.save(order);

        try {
            // create a checkout session
            var builder = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(websiteUrl + "/checkout-success?order_id=" + order.getId())
                    .setCancelUrl(websiteUrl + "/checkout-cancel");

            order.getItems().forEach(item -> {
                var lineItem = SessionCreateParams.LineItem.builder()
                        .setQuantity(Long.valueOf(item.getQuantity()))
                        .setPriceData(
                                SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency("usd")
                                        .setUnitAmountDecimal(item.getUnitPrice().multiply(BigDecimal.valueOf(100))) // price must be in cents
                                        .setProductData(
                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                        .setName(item.getProduct().getName())
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();

                builder.addLineItem(lineItem);
            });

            // could throw stripe exception -> include 'throws StripeException' to method signature...
            // good enough for now...
            Session session = Session.create(builder.build());

            cartService.clearCart(cartId);

            return new CheckoutResponse(order.getId(), session.getUrl());
        }
        catch(StripeException ex) {
            System.out.println(ex.getMessage()); // temporary!
            orderRepository.delete(order); // delete because client could attempt multiple times & create order with no meaning in our application
            throw ex;
        }
    }
}
