package com.ryansstore.store.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.UUID;
import java.util.Set;
import java.util.HashSet;

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

    @OneToMany(mappedBy = "cart")
    private Set<CartItem> cartItems = new HashSet<>();
}
