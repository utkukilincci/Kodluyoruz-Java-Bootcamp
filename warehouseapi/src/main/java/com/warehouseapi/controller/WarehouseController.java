package com.warehouseapi.controller;

import com.warehouseapi.base.WarehouseApiResponse;
import com.warehouseapi.models.dto.BaseIdDto;
import com.warehouseapi.models.dto.WarehouseDto;
import com.warehouseapi.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @GetMapping
    public WarehouseApiResponse<Collection<WarehouseDto>> getAllWarehouses() {
        return warehouseService.list();
    }

    @PostMapping
    public WarehouseApiResponse<WarehouseDto> create(@RequestBody WarehouseDto warehouseDTO) {
        return warehouseService.create(warehouseDTO);
    }

    @PutMapping
    public WarehouseApiResponse<WarehouseDto> update(@RequestBody WarehouseDto warehouseDTO) {
        return warehouseService.update(warehouseDTO);
    }

    @DeleteMapping
    public WarehouseApiResponse<?> delete(@RequestBody BaseIdDto id) {
        return warehouseService.delete(id);
    }

}