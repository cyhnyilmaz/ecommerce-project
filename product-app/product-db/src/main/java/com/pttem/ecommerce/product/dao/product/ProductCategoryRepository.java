package com.pttem.ecommerce.product.dao.product;

import com.pttem.ecommerce.product.dao.base.BaseRepository;
import com.pttem.ecommerce.product.domain.base.ProductCategoryEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ProductCategoryRepository extends BaseRepository<ProductCategoryEntity, Long> {
}
