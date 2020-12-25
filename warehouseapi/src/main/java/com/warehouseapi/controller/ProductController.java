package com.warehouseapi.controller;

import com.warehouseapi.base.WarehouseApiResponse;
import com.warehouseapi.models.dto.BaseIdDto;
import com.warehouseapi.models.dto.ProductDto;
import com.warehouseapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public WarehouseApiResponse<Collection<ProductDto>> getAllProducts() {
        return productService.list();
    }

    @PostMapping
    public WarehouseApiResponse<ProductDto> create(@RequestBody ProductDto productDto) {
        return productService.create(productDto);
    }

    @PutMapping
    public WarehouseApiResponse<ProductDto> update(@RequestBody ProductDto productDto) {
        return productService.update(productDto);
    }

    @DeleteMapping
    public WarehouseApiResponse<?> delete(@RequestBody BaseIdDto id) {
        return productService.delete(id);
    }

}
