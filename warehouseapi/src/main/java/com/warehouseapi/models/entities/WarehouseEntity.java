package com.warehouseapi.models.entities;

import com.warehouseapi.models.enums.WarehouseStatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class WarehouseEntity extends BaseEntity {

    @Column(unique = true, length = 50, nullable = false)
    private String code;

    @Column(length = 50, nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 7, nullable = false)
    private WarehouseStatusEnum status = WarehouseStatusEnum.ACTIVE;

}
