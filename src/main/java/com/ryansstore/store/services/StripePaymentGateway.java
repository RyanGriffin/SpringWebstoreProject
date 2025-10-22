package com.ryansstore.store.services;

import com.ryansstore.store.entities.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.exception.StripeException;
import com.ryansstore.store.exceptions.PaymentException;
import com.ryansstore.store.entities.Order;
import java.math.BigDecimal;

@Service
public class StripePaymentGateway implements PaymentGateway {
    @Value("${websiteUrl}")
    private String websiteUrl;

    @Override
    public CheckoutSession createCheckoutSession(Order order) {
        try {
            var builder = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(websiteUrl + "/checkout-success?order_id=" + order.getId())
                    .setCancelUrl(websiteUrl + "/checkout-cancel")
                    .putMetadata("order_id", order.getId().toString());

            order.getItems().forEach(item -> builder.addLineItem(createLineItem(item)));

            Session session = Session.create(builder.build());

            return new CheckoutSession(session.getId());
        }
        catch(StripeException ex) {
            // in an actual prod environment, we would log this exception inside a logging service (i.e. Sentry)
            // I may add this later for extra practice, but for now, this is outside the scope of this project
            System.out.println(ex.getMessage()); // for now, I will just print this exception message on console...
            throw new PaymentException();
        }
    }

    private SessionCreateParams.LineItem createLineItem(OrderItem item) {
        return SessionCreateParams.LineItem.builder()
                .setQuantity(Long.valueOf(item.getQuantity()))
                .setPriceData(createPriceData(item))
                .build();
    }

    private SessionCreateParams.LineItem.PriceData createPriceData(OrderItem item) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmountDecimal(item.getUnitPrice().multiply(BigDecimal.valueOf(100))) // price must be in cents!
                .setProductData(createProductData(item))
                .build();
    }

    private SessionCreateParams.LineItem.PriceData.ProductData createProductData(OrderItem item) {
        return SessionCreateParams.LineItem.PriceData.ProductData.builder()
                .setName(item.getProduct().getName())
                .build();
    }
}
