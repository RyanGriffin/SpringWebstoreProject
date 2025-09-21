package com.ryansstore.store.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "date_created", insertable = false, updatable = false)
    private Date dateCreated;

    @OneToMany( mappedBy = "cart",
                cascade = {CascadeType.MERGE, CascadeType.REMOVE},
                orphanRemoval = true,
                fetch = FetchType.EAGER)
    private Set<CartItem> items = new HashSet<>();

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for(CartItem cartItem : items)
            totalPrice = totalPrice.add(cartItem.getTotalPrice());
        return totalPrice;
    }

    public CartItem getItem(Long productId) {
        return items.stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public CartItem addItem(Product product) {
        CartItem cartItem = getItem(product.getId());

        if(cartItem != null)
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        else {
            cartItem = CartItem.builder()
                    .product(product)
                    .cart(this)
                    .build();
            items.add(cartItem);
        }

        return cartItem;
    }

    public boolean removeItem(Long productId) {
        CartItem cartItem = getItem(productId);
        boolean removed = false;

        if(cartItem != null) {
            removed = items.remove(cartItem);
            cartItem.setCart(null);
        }

        return removed;
    }

    public void clear() { items.clear(); }
}
