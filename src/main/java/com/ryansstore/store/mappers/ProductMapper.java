package com.ryansstore.store.mappers;

import com.ryansstore.store.dtos.ProductDto;
import com.ryansstore.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categoryId", expression = "java(product.getCategory().getId())")
    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

    @Mapping(target = "id", ignore = true)
    void updateEntity(ProductDto productDto, @MappingTarget Product product);
}
