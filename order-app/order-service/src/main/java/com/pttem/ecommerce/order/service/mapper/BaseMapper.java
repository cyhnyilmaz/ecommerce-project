package com.pttem.ecommerce.order.service.mapper;

import com.pttem.ecommerce.order.domain.base.BaseEntity;
import com.pttem.ecommerce.order.dto.base.BaseDto;

import java.util.List;

public interface BaseMapper <E extends BaseEntity, D extends BaseDto>{

    E dtoToEntity(D dto);

    D entityToDto(E entity);

    List<E> dtoListToEntityList(List<D> dtoList);

    List<D> entityListToDtoList(List<E> entityList);

}
