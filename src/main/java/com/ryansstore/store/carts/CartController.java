package com.ryansstore.store.carts;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import com.ryansstore.store.carts.dtos.CartDto;
import com.ryansstore.store.carts.dtos.CartItemDto;
import com.ryansstore.store.carts.dtos.CartItemQuantityRequest;
import com.ryansstore.store.carts.dtos.CartAddItemRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import java.net.URI;

@Tag(name = "Carts")
@AllArgsConstructor
@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartDto> createCart(UriComponentsBuilder uriBuilder) {
        CartDto cartDto = cartService.createCart();
        URI uri =  uriBuilder.path("/carts/{id}").buildAndExpand(cartDto.getId()).toUri();

        return ResponseEntity.created(uri).body(cartDto);
    }

    @GetMapping("/{cartId}")
    public CartDto getCart(@PathVariable UUID cartId) {
        return cartService.getCart(cartId);
    }

    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<Void> clearCart(@PathVariable UUID cartId) {
        cartService.clearCart(cartId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItemDto> addToCart(@PathVariable UUID cartId, @RequestBody CartAddItemRequest request) {
        CartItemDto cartItemDto = cartService.addToCart(cartId, request.getProductId());

        return ResponseEntity.status(201).body(cartItemDto);
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<Void> removeItem(@PathVariable UUID cartId, @PathVariable Long productId) {
        cartService.removeItem(cartId, productId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{cartId}/items/{productId}")
    public CartItemDto updateItemQuantity(
            @PathVariable UUID cartId,
            @PathVariable Long productId,
            @Valid @RequestBody CartItemQuantityRequest request) {
        return cartService.updateItemQuantity(cartId, productId, request.getQuantity());
    }
}
