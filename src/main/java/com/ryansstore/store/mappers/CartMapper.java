package com.ryansstore.store.mappers;

import org.mapstruct.Mapper;
import com.ryansstore.store.entities.Cart;
import com.ryansstore.store.dtos.CartDto;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toDto(Cart cart);
}
