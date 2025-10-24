package com.ryansstore.store.orders;

import org.mapstruct.Mapper;
import com.ryansstore.store.orders.dtos.OrderDto;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
}