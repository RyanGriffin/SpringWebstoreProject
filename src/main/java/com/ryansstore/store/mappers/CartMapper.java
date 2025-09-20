package com.ryansstore.store.mappers;

import com.ryansstore.store.entities.Cart;
import com.ryansstore.store.dtos.CartDto;
import org.mapstruct.Mapper;

@Mapper
public interface CartMapper {
    CartDto toDto(Cart cart);
}
