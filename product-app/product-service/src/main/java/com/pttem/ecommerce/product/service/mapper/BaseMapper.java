package com.pttem.ecommerce.product.service.mapper;

import com.pttem.ecommerce.product.domain.base.BaseEntity;
import com.pttem.ecommerce.product.dto.base.BaseDto;

import java.util.List;

public interface BaseMapper <E extends BaseEntity, D extends BaseDto>{

    E dtoToEntity(D dto);

    D entityToDto(E entity);

    List<E> dtoListToEntityList(List<D> dtoList);

    List<D> entityListToDtoList(List<E> entityList);

}
