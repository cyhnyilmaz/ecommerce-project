package com.pttem.ecommerce.user.service.mapper;

import com.pttem.ecommerce.user.domain.base.BaseEntity;
import com.pttem.ecommerce.user.dto.base.BaseDto;

import java.util.List;

public interface BaseMapper <E extends BaseEntity, D extends BaseDto>{

    E dtoToEntity(D dto);

    D entityToDto(E entity);

    List<E> dtoListToEntityList(List<D> dtoList);

    List<D> entityListToDtoList(List<E> entityList);

}
