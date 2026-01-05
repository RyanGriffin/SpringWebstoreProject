package com.ryansstore.store.payments;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import com.stripe.model.StripeObject;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.net.Webhook;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.ryansstore.store.orders.Order;
import com.ryansstore.store.orders.OrderItem;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class StripePaymentGateway implements PaymentGateway {
    @Value("${websiteUrl}")
    private String websiteUrl;

    @Value("${stripe.webhookSecretKey}")
    private String webhookSecretKey;

    @Override
    public CheckoutSession createCheckoutSession(Order order) {
        try {
            SessionCreateParams.Builder builder = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(websiteUrl + "/checkout-success?order_id=" + order.getId())
                    .setCancelUrl(websiteUrl + "/checkout-cancel")
                    .setPaymentIntentData(createPaymentIntent(order));

            order.getItems().forEach(item -> builder.addLineItem(createLineItem(item)));

            Session session = Session.create(builder.build());

            return new CheckoutSession(session.getId(), session.getUrl());
        }
        catch(StripeException ex) {
            // in an actual prod environment, we would log this exception inside a logging service (i.e. Sentry)
            // I may add this later for extra practice, but for now, this is outside the scope of this project
            System.out.println(ex.getMessage()); // for now, I will just print this exception message on console...
            throw new PaymentException();
        }
    }

    @Override
    public Optional<PaymentResult> parseWebhookRequest(WebhookRequest request) {
        try {
            String payload = request.getPayload();
            String signature = request.getHeaders().get("stripe-signature"); // spring normalizes header names to lowercase
            Event event =  Webhook.constructEvent(payload, signature, webhookSecretKey);

            return switch(event.getType()) {
                case "payment_intent.succeeded" ->
                        Optional.of(new PaymentResult(extractOrderId(event), PaymentStatus.PAID));

                case "payment_intent.payment_failed" ->
                        Optional.of(new PaymentResult(extractOrderId(event), PaymentStatus.FAILED));

                default -> Optional.empty();
            };
        }
        catch (SignatureVerificationException e) {
            throw new PaymentException("invalid signature");
        }
    }

    private static SessionCreateParams.PaymentIntentData createPaymentIntent(Order order) {
        return SessionCreateParams.PaymentIntentData.builder()
                .putMetadata("order_id", order.getId().toString())
                .build();
    }

    private Long extractOrderId(Event event) {
        StripeObject stripeObject = event.getDataObjectDeserializer().getObject().orElseThrow(() ->
                new PaymentException("Could not deserialize Stripe event. Check SDK and API version")
        );

        PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
        return Long.valueOf(paymentIntent.getMetadata().get("order_id"));
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
