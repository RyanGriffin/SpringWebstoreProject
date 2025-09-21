package com.ryansstore.store.controllers;

import com.ryansstore.store.dtos.CartItemQuantityRequest;
import com.ryansstore.store.entities.Cart;
import com.ryansstore.store.entities.CartItem;
import com.ryansstore.store.entities.Product;
import com.ryansstore.store.dtos.CartDto;
import com.ryansstore.store.dtos.CartItemDto;
import com.ryansstore.store.dtos.CartAddItemRequest;
import com.ryansstore.store.mappers.CartMapper;
import com.ryansstore.store.repositories.CartRepository;
import com.ryansstore.store.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import java.net.URI;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("DuplicatedCode")
@AllArgsConstructor
@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    @PostMapping
    public ResponseEntity<CartDto> createCart(UriComponentsBuilder uriBuilder) {
        Cart newCart = new Cart();
        cartRepository.save(newCart);

        CartDto cartDto = cartMapper.toDto(newCart);
        URI uri =  uriBuilder.path("/carts/{id}").buildAndExpand(cartDto.getId()).toUri();

        return ResponseEntity.created(uri).body(cartDto);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable UUID cartId) {
        Cart cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(cartMapper.toDto(cart));
    }

    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<?> clearCart(@PathVariable UUID cartId) {
        Cart cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null) // return 404 if cart doesn't exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "cart not found!"));

        cart.clear();
        cartRepository.save(cart);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItemDto> addToCart(
            @PathVariable UUID cartId,
            @RequestBody CartAddItemRequest cartAddItemRequest) {
        Cart cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null) // return 404 if cart doesn't exist
            return ResponseEntity.notFound().build();

        Product product = productRepository.findById(cartAddItemRequest.getProductId()).orElse(null);
        if(product == null) // return 400 if product doesn't exist
            return ResponseEntity.badRequest().build();

        CartItem cartItem = cart.addItem(product);
        cartRepository.save(cart);

        return ResponseEntity.status(201).body(cartMapper.toDto(cartItem));
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> removeFromCart(
            @PathVariable UUID cartId,
            @PathVariable Long productId) {
        Cart cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null) // return 404 if cart doesn't exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "cart not found!"));

        CartItem item = cart.getItem(productId);
        if(item == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "item not found!"));

        if(cart.removeItem(productId)) {
            cartRepository.save(cart);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build(); // item not in cart, return 404
    }

    @PutMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> updateItemQuantity(
            @PathVariable UUID cartId,
            @PathVariable Long productId,
            @Valid @RequestBody CartItemQuantityRequest request) {
        Cart cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null) // return 404 if cart doesn't exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "cart not found!"));

        CartItem item = cart.getItem(productId);
        if(item == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "item not found!"));

        item.setQuantity(request.getQuantity());
        cartRepository.save(cart);

        return ResponseEntity.ok(cartMapper.toDto(item));
    }
}
