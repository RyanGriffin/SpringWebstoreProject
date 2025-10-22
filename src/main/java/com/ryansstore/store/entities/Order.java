package com.ryansstore.store.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @OneToMany( mappedBy = "order",
                cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
                fetch = FetchType.EAGER)
    private Set<OrderItem> items = new LinkedHashSet<>();

    public static Order fromCart(Cart cart, User customer) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setStatus(OrderStatus.PENDING);
        order.setTotalPrice(cart.getTotalPrice());

        cart.getItems().forEach(item -> {
            OrderItem orderItem = new OrderItem(order, item.getProduct(), item.getQuantity());
            order.items.add(orderItem);
        });

        return order;
    }

    public boolean isPlacedBy(User customer) {
        return this.customer.equals(customer);
    }
}
