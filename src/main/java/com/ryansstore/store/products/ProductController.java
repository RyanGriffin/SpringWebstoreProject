package com.ryansstore.store.products;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ryansstore.store.common.ErrorDto;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.net.URI;
import java.util.List;

@Tag(name = "Products")
@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Retrieves a list of all products.")
    @GetMapping
    public List<ProductDto> getAllProducts(
            @Parameter(description = "ID of the category.")
            @RequestParam(name = "categoryId", required = false) Byte id) {
        return productService.getAllProducts(id);
    }

    @Operation(summary = "Retrieves a specific product.")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(
            @Parameter(description = "ID of the product.")
            @PathVariable Long id) {
        return productService.getProductById(id);
    }

    @Operation(summary = "Creates a new product.")
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto, UriComponentsBuilder uriBuilder) {
        productService.createProduct(productDto);
        URI uri =  uriBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();

        return ResponseEntity.created(uri).body(productDto);
    }

    @Operation(summary = "Updates a product's information.")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @Parameter(description = "ID of the product.")
            @PathVariable Long id,
            @RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto, id);
    }

    @Operation(summary = "Deletes a product.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "ID of the product.")
            @PathVariable(name = "id") Long id) {
        return productService.deleteProduct(id);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCategoryNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("category not found!"));
    }
}
