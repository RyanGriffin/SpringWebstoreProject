package com.ryansstore.store.controllers;

import com.ryansstore.store.entities.Cart;
import com.ryansstore.store.repositories.CartRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import lombok.AllArgsConstructor;
import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartRepository cartRepository;

    @PostMapping
    public ResponseEntity<Cart> createCart(UriComponentsBuilder uriBuilder) {
        Cart newCart = new Cart();
        cartRepository.save(newCart);

        URI uri =  uriBuilder.path("/carts/{id}").buildAndExpand(newCart.getId()).toUri();

        return ResponseEntity.created(uri).body(newCart);
    }
}
