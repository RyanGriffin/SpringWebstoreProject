package com.ryansstore.store.products;

import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import lombok.AllArgsConstructor;
import java.net.URI;
import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> getAllProducts(Byte id) {
        if(id != null) {
            return productRepository.findByCategoryId(id)
                    .stream()
                    .map(productMapper::toDto)
                    .toList();
        }

        return productRepository.findAllWithCategory()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    public ResponseEntity<ProductDto> getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null)
            throw new ProductNotFoundException();

        return ResponseEntity.ok(productMapper.toDto(product));
    }

    public ResponseEntity<ProductDto> createProduct(ProductDto productDto, UriComponentsBuilder uriBuilder) {
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(CategoryNotFoundException::new);

        Product product = productMapper.toEntity(productDto);
        product.setCategory(category);
        productRepository.save(product);
        productDto.setId(product.getId());

        URI uri =  uriBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();

        return ResponseEntity.created(uri).body(productDto);
    }

    public ResponseEntity<ProductDto> updateProduct(ProductDto productDto, Long id) {
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if(category == null)
            throw new CategoryNotFoundException();

        Product product = productRepository.findById(id).orElse(null);
        if(product == null)
            throw new ProductNotFoundException();

        productMapper.updateEntity(productDto, product);
        productDto.setId(product.getId());
        product.setCategory(category);
        productRepository.save(product);

        return ResponseEntity.ok(productDto);
    }

    public ResponseEntity<Void> deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null)
            throw new ProductNotFoundException();

        productRepository.delete(product);

        return ResponseEntity.noContent().build();
    }
}
