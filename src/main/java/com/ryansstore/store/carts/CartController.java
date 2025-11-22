package com.ryansstore.store.carts;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ryansstore.store.carts.dtos.CartDto;
import com.ryansstore.store.carts.dtos.CartItemDto;
import com.ryansstore.store.carts.dtos.CartItemQuantityRequest;
import com.ryansstore.store.carts.dtos.CartAddItemRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.UUID;
import java.net.URI;

@Tag(name = "Carts")
@AllArgsConstructor
@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    @Operation(summary = "Creates a new cart.")
    @PostMapping
    public ResponseEntity<CartDto> createCart(UriComponentsBuilder uriBuilder) {
        CartDto cartDto = cartService.createCart();
        URI uri =  uriBuilder.path("/carts/{id}").buildAndExpand(cartDto.getId()).toUri();

        return ResponseEntity.created(uri).body(cartDto);
    }

    @Operation(summary = "Retrieves a cart.")
    @GetMapping("/{cartId}")
    public CartDto getCart(
            @Parameter(description = "ID of the cart.")
            @PathVariable UUID cartId) {
        return cartService.getCart(cartId);
    }

    @Operation(summary = "Adds a product to the cart.")
    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItemDto> addToCart(
            @Parameter(description = "ID of the cart.")
            @PathVariable UUID cartId,
            @RequestBody CartAddItemRequest request) {
        CartItemDto cartItemDto = cartService.addToCart(cartId, request.getProductId());

        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }

    @Operation(summary = "Updates quantity of a product in the cart.")
    @PutMapping("/{cartId}/items/{productId}")
    public CartItemDto updateItemQuantity(
            @Parameter(description = "ID of the cart.")
            @PathVariable UUID cartId,
            @Parameter(description = "ID of the product.")
            @PathVariable Long productId,
            @Valid @RequestBody CartItemQuantityRequest request) {
        return cartService.updateItemQuantity(cartId, productId, request.getQuantity());
    }

    @Operation(summary = "Removes a product from the cart.")
    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<Void> removeItem(
            @Parameter(description = "ID of the cart.")
            @PathVariable UUID cartId,
            @Parameter(description = "ID of the product.")
            @PathVariable Long productId) {
        cartService.removeItem(cartId, productId);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Removes all items from the cart.")
    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<Void> clearCart(
            @Parameter(description = "ID of the cart.")
            @PathVariable UUID cartId) {
        cartService.clearCart(cartId);

        return ResponseEntity.noContent().build();
    }
}
