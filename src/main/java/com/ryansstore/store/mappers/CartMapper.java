package com.ryansstore.store.mappers;

import com.ryansstore.store.dtos.CartItemDto;
import com.ryansstore.store.entities.CartItem;
import org.mapstruct.Mapper;
import com.ryansstore.store.entities.Cart;
import com.ryansstore.store.dtos.CartDto;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toDto(Cart cart);

    CartItemDto toDto(CartItem cartItem);
}
