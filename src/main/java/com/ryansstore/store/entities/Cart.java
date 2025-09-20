package com.ryansstore.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "date_created")
    private Date dateCreated;
}
