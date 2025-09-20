package com.ryansstore.store.mappers;

import org.mapstruct.Mapper;
import com.ryansstore.store.entities.Cart;
import com.ryansstore.store.dtos.CartDto;
import com.ryansstore.store.entities.CartItem;
import com.ryansstore.store.dtos.CartItemDto;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toDto(Cart cart);

    CartItemDto toDto(CartItem cartItem);
}
