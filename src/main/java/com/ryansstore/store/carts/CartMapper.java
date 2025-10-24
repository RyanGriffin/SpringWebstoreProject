package com.ryansstore.store.carts;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.ryansstore.store.carts.dtos.CartDto;
import com.ryansstore.store.carts.dtos.CartItemDto;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
