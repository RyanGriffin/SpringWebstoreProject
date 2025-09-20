package com.ryansstore.store.mappers;

import com.ryansstore.store.dtos.CartItemDto;
import com.ryansstore.store.entities.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemDto toDto(CartItem cartItem);
}
