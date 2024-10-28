package com.pttem.ecommerce.order.service.base;


import com.pttem.ecommerce.order.constant.exceptions.ServiceException;
import com.pttem.ecommerce.order.dto.base.BaseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;


public interface BaseCrudService<D extends BaseDto> {
    D create(D dto) throws ServiceException;

    List<D> createAll(List<D> dto) throws ServiceException;

    D read(Long id) throws ServiceException;

    List<D> read();

    Page<D> read(/*PageableSearchFilterDto searchFilterDto,*/ PageRequest pageable);

    D update(D dto) throws ServiceException;

    void delete(Long id) throws ServiceException;

    void deleteAll(List<Long> idList) throws ServiceException;

}

