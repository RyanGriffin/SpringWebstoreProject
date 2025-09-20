package com.ryansstore.store.controllers;

import com.ryansstore.store.dtos.CartDto;
import com.ryansstore.store.entities.Cart;
import com.ryansstore.store.mappers.CartMapper;
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
    private final CartMapper cartMapper;

    @PostMapping
    public ResponseEntity<CartDto> createCart(UriComponentsBuilder uriBuilder) {
        Cart newCart = new Cart();
        cartRepository.save(newCart);

        CartDto cartDto = cartMapper.toDto(newCart);

        URI uri =  uriBuilder.path("/carts/{id}").buildAndExpand(cartDto.getId()).toUri();

        return ResponseEntity.created(uri).body(cartDto);
    }
}
