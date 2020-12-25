package com.warehouseapi.service.Impl;

import com.warehouseapi.base.WarehouseApiResponse;
import com.warehouseapi.base.WarehouseApiResponseError;
import com.warehouseapi.dao.WarehouseQueryRepository;
import com.warehouseapi.dao.WarehouseRepository;
import com.warehouseapi.models.converters.WarehouseDTOToWarehouseEntity;
import com.warehouseapi.models.converters.WarehouseEntityToWarehouseDTO;
import com.warehouseapi.models.dto.BaseIdDto;
import com.warehouseapi.models.dto.WarehouseDto;
import com.warehouseapi.models.entities.WarehouseEntity;
import com.warehouseapi.service.WarehouseService;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseEntityToWarehouseDTO warehouseEntityToWarehouseDTOConverter;
    private final WarehouseDTOToWarehouseEntity warehouseDTOToWarehouseEntityConverter;
    private final WarehouseQueryRepository warehouseQueryRepository;

    @Override
    public WarehouseApiResponse<Collection<WarehouseDto>> list() {
        Collection<WarehouseEntity> warehouseEntities = warehouseRepository.list();
        if(CollectionUtils.isEmpty(warehouseEntities)){
            return new WarehouseApiResponse<>(HttpStatus.NOT_FOUND);
        }
        List<WarehouseDto> warehouseDTOList = warehouseEntities
                .stream()
                .map(warehouseEntityToWarehouseDTOConverter::convert)
                .collect(Collectors.toList());
        return new WarehouseApiResponse<>(warehouseDTOList, HttpStatus.OK);
    }

    @Override
    public WarehouseApiResponse<WarehouseDto> create(WarehouseDto data) {
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
                    .message("You can not create this warehouse. Already exist a warehouse with Same Code")
                    .build());
        }
        WarehouseEntity warehouseEntity = warehouseDTOToWarehouseEntityConverter.convert(data);
        warehouseRepository.create(warehouseEntity);
        return new WarehouseApiResponse<>(warehouseEntityToWarehouseDTOConverter.
                convert(warehouseEntity),HttpStatus.OK);
    }

    @Override
    public WarehouseApiResponse<WarehouseDto> update(WarehouseDto data) {
        if(warehouseQueryRepository.hasExistWarehouse(data.getId())){
            return new WarehouseApiResponse<>((HttpStatus.NOT_FOUND));
        }
        if(warehouseQueryRepository.hasExistSameWarehouseCode(data.getCode())){
            return new WarehouseApiResponse<>(HttpStatus.NO_CONTENT, WarehouseApiResponseError
                    .builder()
                    .code("DUPLICATE_DATA")
                    .message("You can not update this warehouse with this code. Already exist a warehouse with Same Code")
                    .build());
        }

        WarehouseEntity warehouseEntity = warehouseDTOToWarehouseEntityConverter.convert(data);
        warehouseEntity.setUpdatedDate(new Date());
        WarehouseEntity updatedEntitiy = warehouseRepository.update(warehouseEntity);
        return new WarehouseApiResponse<>((warehouseEntityToWarehouseDTOConverter.convert(updatedEntitiy)),
                HttpStatus.OK);
    }

    @Override
    public WarehouseApiResponse<?> delete(BaseIdDto id) {
        warehouseRepository.delete(id.getId());
        return new WarehouseApiResponse<>(HttpStatus.OK);
    }
}
