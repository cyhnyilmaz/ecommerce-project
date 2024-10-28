package com.pttem.ecommerce.order.dao.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    @Transactional
    void hardDeleteById(ID id);

    @Transactional
    boolean updateField(T entity, HashMap<String, Object> updateList);

    List<T> findAll(Boolean baseStatus);

    <S extends T> List<S> saveAll(Iterable<S> entities);


    @Transactional
    List<T> findAll(Boolean baseStatus, Sort sort);

    List<T> findAll(Boolean baseStatus, String fieldName, Object value);

    @Transactional
    Page<T> findAll(Boolean baseStatus, Pageable pageable);

    T getOne(Boolean baseStatus, ID id);

    Optional<T> findOne(Boolean baseStatus, Specification<T> spec);

    boolean existsById(Boolean baseStatus, ID id);

    List<T> findAllById(Boolean baseStatus, Iterable<ID> ids);
}
