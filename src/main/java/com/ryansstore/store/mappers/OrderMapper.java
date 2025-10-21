package com.ryansstore.store.mappers;

import org.mapstruct.Mapper;
import com.ryansstore.store.entities.Order;
import com.ryansstore.store.dtos.OrderDto;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
}