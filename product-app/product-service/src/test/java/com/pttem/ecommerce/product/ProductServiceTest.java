package com.pttem.ecommerce.product;

import com.pttem.ecommerce.product.dao.product.ProductRepository;
import com.pttem.ecommerce.product.domain.base.ProductEntity;
import com.pttem.ecommerce.product.service.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private ProductEntity productEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setName("Sample Product");
        productEntity.setStock(2L);
    }



}
