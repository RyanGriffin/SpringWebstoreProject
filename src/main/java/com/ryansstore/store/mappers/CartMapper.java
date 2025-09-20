package com.ryansstore.store.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.ryansstore.store.entities.Cart;
import com.ryansstore.store.dtos.CartDto;
import com.ryansstore.store.entities.CartItem;
import com.ryansstore.store.dtos.CartItemDto;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
