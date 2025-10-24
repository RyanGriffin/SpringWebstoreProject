package com.ryansstore.store.carts;

import org.springframework.stereotype.Service;
import com.ryansstore.store.products.Product;
import com.ryansstore.store.products.ProductRepository;
import com.ryansstore.store.carts.dtos.CartDto;
import com.ryansstore.store.carts.dtos.CartItemDto;
import com.ryansstore.store.products.ProductNotFoundException;
import lombok.AllArgsConstructor;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CartService {
    private CartMapper cartMapper;
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    public CartDto createCart() {
        Cart newCart = new Cart();
        cartRepository.save(newCart);

        return cartMapper.toDto(newCart);
    }

    public CartDto getCart(UUID cartId) {
        Cart cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null)
            throw new CartNotFoundException();

        return cartMapper.toDto(cart);
    }

    public void clearCart(UUID cartId) {
        Cart cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null)
            throw new CartNotFoundException();

        cart.clear();
        cartRepository.save(cart);
    }

    public CartItemDto addToCart(UUID cartId, long productId) {
        Cart cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null)
            throw new CartNotFoundException();

        Product product = productRepository.findById(productId).orElse(null);
        if(product == null)
            throw new ProductNotFoundException();

        CartItem cartItem = cart.addItem(product);
        cartRepository.save(cart);

        return cartMapper.toDto(cartItem);
    }

    public void removeItem(UUID cartId, long productId) {
        Cart cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null)
            throw new CartNotFoundException();

        CartItem item = cart.getItem(productId);
        if(item == null)
            throw new ProductNotFoundException();

        if(cart.removeItem(productId))
            cartRepository.save(cart);
        else
            throw new ProductNotFoundException();
    }

    public CartItemDto updateItemQuantity(UUID cartId, long productId, Integer quantity) {
        Cart cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null) // return 404 if cart doesn't exist
            throw new CartNotFoundException();

        CartItem item = cart.getItem(productId);
        if(item == null)
            throw new ProductNotFoundException();

        item.setQuantity(quantity);
        cartRepository.save(cart);

        return cartMapper.toDto(item);
    }
}
