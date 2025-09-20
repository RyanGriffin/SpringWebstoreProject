package com.ryansstore.store.controllers;

import com.ryansstore.store.dtos.CartItemDto;
import com.ryansstore.store.entities.Cart;
import com.ryansstore.store.entities.CartItem;
import com.ryansstore.store.entities.Product;
import com.ryansstore.store.dtos.CartAddItemRequest;
import com.ryansstore.store.dtos.CartDto;
import com.ryansstore.store.mappers.CartMapper;
import com.ryansstore.store.repositories.CartRepository;
import com.ryansstore.store.repositories.ProductRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import lombok.AllArgsConstructor;
import java.net.URI;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<CartDto> createCart(UriComponentsBuilder uriBuilder) {
        Cart newCart = new Cart();
        cartRepository.save(newCart);

        CartDto cartDto = cartMapper.toDto(newCart);
        URI uri =  uriBuilder.path("/carts/{id}").buildAndExpand(cartDto.getId()).toUri();

        return ResponseEntity.created(uri).body(cartDto);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItemDto> addToCart(
            @PathVariable UUID cartId,
            @RequestBody CartAddItemRequest cartAddItemRequest,
            UriComponentsBuilder uriBuilder) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if(cart == null) // return 404 if cart doesn't exist
            return ResponseEntity.notFound().build();

        Product product = productRepository.findById(cartAddItemRequest.getProductId()).orElse(null);
        if(product == null) // return 400 if product doesn't exist
            return ResponseEntity.badRequest().build();

        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if(cartItem != null)
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        else {
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItem.setCart(cart);
            cart.getCartItems().add(cartItem);
        }

        cartRepository.save(cart);

        CartItemDto cartItemDto = cartMapper.toDto(cartItem);
        URI uri =  uriBuilder.path("/carts/{id}/items").buildAndExpand(cartItemDto.getProduct().getId()).toUri();

        return ResponseEntity.created(uri).body(cartItemDto);
    }
}
