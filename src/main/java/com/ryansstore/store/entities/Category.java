package com.ryansstore.store.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

// @ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Byte id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Product> products = new HashSet<>();

    public void addProduct(Product product) {
        products.add(product);
        product.setCategory(this);
    }
}