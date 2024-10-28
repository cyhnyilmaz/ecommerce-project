package com.pttem.ecommerce.order.service.base;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pttem.ecommerce.order.constant.enums.EnumCrudExceptionMessages;
import com.pttem.ecommerce.order.constant.exceptions.ServiceException;
import com.pttem.ecommerce.order.dao.base.BaseRepository;
import com.pttem.ecommerce.order.domain.base.BaseEntity;
import com.pttem.ecommerce.order.dto.base.BaseDto;
import com.pttem.ecommerce.order.service.mapper.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BaseCrudServiceImpl<E extends BaseEntity, D extends BaseDto> implements BaseCrudService<D> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseCrudServiceImpl.class);

    protected final BaseRepository<E, Long> baseRepository;

    private final BaseMapper<E, D> baseMapper;
    private ObjectMapper objectMapper;


    public BaseCrudServiceImpl(@Qualifier("baseRepositoryCustomizedImpl") BaseRepository<E, Long> baseRepository, BaseMapper<E, D> baseMapper) {
        this.baseRepository = baseRepository;
        this.baseMapper = baseMapper;
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public D create(D dto) {
        E entity = baseMapper.dtoToEntity(dto);

        if (Objects.isNull(entity)) {
            throw new ServiceException(EnumCrudExceptionMessages.OBJECT_NOT_BE_EMPTY);
        }

        return baseMapper.entityToDto(baseRepository.save(entity));
    }

    @Override
    public List<D> createAll(List<D> dtoList) {
        List<E> entityList = new ArrayList<>();

        for (D dto : dtoList) {
            E entity = baseMapper.dtoToEntity(dto);

            if (Objects.isNull(entity)) {
                throw new ServiceException(EnumCrudExceptionMessages.OBJECT_NOT_BE_EMPTY);
            }
            entityList.add(entity);
        }
        return baseMapper.entityListToDtoList(baseRepository.saveAll(entityList));
    }

    @Override
    public D read(Long id) {
        if (Objects.isNull(id)) {
            throw new ServiceException(EnumCrudExceptionMessages.ID_NOT_BE_EMPTY);
        }
        E result = baseRepository.getOne(true, id);
        return baseMapper.entityToDto(result);
    }

    @Override
    public List<D> read() {
        return baseMapper.entityListToDtoList(baseRepository.findAll(true));
    }


    @Override
    public Page<D> read(/*PageableSearchFilterDto searchFilterDto,*/ PageRequest pageable) {
//        Specification<E> builtSpecification = getBuildSpecification(searchFilterDto);
//        Page<E> page = baseRepository.findAll(builtSpecification, pageable);
//        return new PageImpl<>(baseMapper.entityListToDtoList(page.getContent()), pageable, page.getTotalElements());
        return null;
    }

    @Override
    public D update(D dto) {

        E entity = baseMapper.dtoToEntity(dto);

        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            throw new ServiceException(EnumCrudExceptionMessages.OBJECT_OR_ID_NOT_BE_EMPTY_DURING_UPDATE);
        }

        return baseMapper.entityToDto(baseRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        if (Objects.isNull(id)) {
            throw new ServiceException(EnumCrudExceptionMessages.ID_NOT_BE_EMPTY_FOR_DELETE);
        }

        E entity = baseRepository.getOne(true, id);

        baseRepository.hardDeleteById(id);
    }

    @Override
    @SuppressWarnings("squid:S2259")
    public void deleteAll(List<Long> idList) {
        List<E> entityList = new ArrayList<>();

        for (Long id : idList) {
            if (Objects.isNull(id)) {
                throw new ServiceException(EnumCrudExceptionMessages.ID_NOT_BE_EMPTY_FOR_DELETE);
            }

            E entity = baseRepository.getOne(true, id);
            if (Objects.isNull(entity)) {
                throw new ServiceException(EnumCrudExceptionMessages.ENTITY_NOT_FOUND_FOR_DELETE);
            }


            baseRepository.hardDeleteById(id);
            entityList.add(entity);
        }
    }



}
