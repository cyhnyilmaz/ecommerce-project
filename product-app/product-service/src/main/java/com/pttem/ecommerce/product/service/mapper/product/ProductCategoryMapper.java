package com.pttem.ecommerce.product.service.mapper.product;

import com.pttem.ecommerce.product.constant.converter.EnumProductGenderConverter;
import com.pttem.ecommerce.product.domain.base.ProductCategoryEntity;
import com.pttem.ecommerce.product.dto.product.ProductCategoryDto;
import com.pttem.ecommerce.product.service.mapper.BaseMapper;
import com.pttem.ecommerce.product.service.mapper.config.GeneralMapStructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = GeneralMapStructConfig.class, uses = {
        EnumProductGenderConverter.class
})
public interface ProductCategoryMapper extends BaseMapper<ProductCategoryEntity, ProductCategoryDto> {
    ProductCategoryMapper INSTANCE = Mappers.getMapper(ProductCategoryMapper.class);

}
