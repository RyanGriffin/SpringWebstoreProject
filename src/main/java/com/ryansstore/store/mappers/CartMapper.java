package com.ryansstore.store.mappers;

import org.mapstruct.Mapper;
import com.ryansstore.store.entities.Cart;
import com.ryansstore.store.dtos.CartDto;
import com.ryansstore.store.entities.CartItem;
import com.ryansstore.store.dtos.CartItemDto;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
