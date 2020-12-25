package com.warehouseapi.service.Impl;

import com.warehouseapi.base.WarehouseApiResponse;
import com.warehouseapi.base.WarehouseApiResponseError;
import com.warehouseapi.dao.ProductRepository;
import com.warehouseapi.dao.WarehouseQueryRepository;
import com.warehouseapi.models.converters.ProductDTOToProductEntity;
import com.warehouseapi.models.converters.ProductEntityToProductDto;
import com.warehouseapi.models.dto.BaseIdDto;
import com.warehouseapi.models.dto.ProductDto;
import com.warehouseapi.models.dto.WarehouseDto;
import com.warehouseapi.models.entities.ProductEntity;
import com.warehouseapi.models.entities.WarehouseEntity;
import com.warehouseapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDTOToProductEntity productDTOToProductEntity;
    private final ProductEntityToProductDto productEntityToProductDto;
    private final WarehouseQueryRepository warehouseQueryRepository;

    @Override
    public WarehouseApiResponse<Collection<ProductDto>> list() {
        Collection<ProductEntity> productEntities = productRepository.list();
        if(CollectionUtils.isEmpty(productEntities)){
            return new WarehouseApiResponse<>(HttpStatus.NOT_FOUND);
        }
        List<ProductDto> productDtoList = productEntities
                .stream()
                .map(productEntityToProductDto::convert)
                .collect(Collectors.toList());
        return new WarehouseApiResponse<>(productDtoList, HttpStatus.OK);
    }

    @Override
    public WarehouseApiResponse create(ProductDto data) {

        if(Objects.isNull(data)){
            return new WarehouseApiResponse<>(HttpStatus.NO_CONTENT);
        }
        String name = data.getName();
        if(name.isEmpty()){
            return new WarehouseApiResponse<>((HttpStatus.NO_CONTENT));
        }
        if(warehouseQueryRepository.hasExistSameWarehouseCode(data.getCode())){
            return new WarehouseApiResponse<>(HttpStatus.NO_CONTENT, WarehouseApiResponseError
                    .builder()
                    .code("DUPLICATE_DATA")
                    .message("You can not create this product. Already exist a product with Same Code")
                    .build());
        }
        ProductEntity productEntity = productDTOToProductEntity.convert(data);
        productRepository.create(productEntity);
        return new WarehouseApiResponse<>(productEntityToProductDto.
                convert(productEntity),HttpStatus.OK);
    }

    @Override
    public WarehouseApiResponse update(ProductDto data) {

        if(warehouseQueryRepository.hasExistProduct(data.getId())){
            return new WarehouseApiResponse<>((HttpStatus.NOT_FOUND));
        }

        if(warehouseQueryRepository.hasExistSameWarehouseCode(data.getCode())){
            return new WarehouseApiResponse<>(HttpStatus.NO_CONTENT, WarehouseApiResponseError
                    .builder()
                    .code("DUPLICATE_DATA")
                    .message("You can not update this product with this code. Already exist a product with Same Code")
                    .build());
        }

        ProductEntity productEntity = productDTOToProductEntity.convert(data);
        productEntity.setUpdatedDate(new Date());
        ProductEntity updatedEntity = productRepository.update(productEntity);
        return new WarehouseApiResponse<>((productEntityToProductDto.convert(updatedEntity)),
                HttpStatus.OK);
    }

    @Override
    public WarehouseApiResponse<?> delete(BaseIdDto id) {
        productRepository.delete(id.getId());
        return new WarehouseApiResponse<>(HttpStatus.OK);
    }
}
