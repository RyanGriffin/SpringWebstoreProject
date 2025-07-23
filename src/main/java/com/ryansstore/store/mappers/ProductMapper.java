package com.ryansstore.store.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.ryansstore.store.entities.Product;
import com.ryansstore.store.dtos.ProductDto;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categoryId", expression = "java(product.getCategory().getId())")
    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

    void updateEntity(ProductDto productDto, @MappingTarget Product product);
}
