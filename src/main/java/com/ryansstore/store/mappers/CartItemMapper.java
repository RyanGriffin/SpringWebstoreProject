package com.ryansstore.store.mappers;

import org.mapstruct.Mapper;
import com.ryansstore.store.dtos.CartItemDto;
import com.ryansstore.store.entities.CartItem;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemDto toDto(CartItem cartItem);
}
