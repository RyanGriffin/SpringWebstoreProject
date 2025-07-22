package com.ryansstore.store.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.ryansstore.store.dtos.ProductDto;
import com.ryansstore.store.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categoryId", expression = "java(product.getCategory().getId())")
    ProductDto toDto(Product product);
}
