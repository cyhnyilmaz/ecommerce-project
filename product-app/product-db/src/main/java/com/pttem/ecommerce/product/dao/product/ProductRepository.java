
package com.pttem.ecommerce.product.dao.product;

import com.pttem.ecommerce.product.dao.base.BaseRepository;
import com.pttem.ecommerce.product.domain.base.ProductEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface ProductRepository extends BaseRepository<ProductEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    @Query(value = "SELECT p FROM product p WHERE p.id = :id", nativeQuery = true)
    Optional<ProductEntity> findById(@Param("id") Long id);

}
