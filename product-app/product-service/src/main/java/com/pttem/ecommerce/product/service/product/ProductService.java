package com.pttem.ecommerce.product.service.product;

import com.pttem.ecommerce.product.dto.product.ProductDto;
import com.pttem.ecommerce.product.service.base.BaseCrudService;

public interface ProductService extends BaseCrudService<ProductDto> {

    void updateProductStock(Long productId, Long newStock);
}
