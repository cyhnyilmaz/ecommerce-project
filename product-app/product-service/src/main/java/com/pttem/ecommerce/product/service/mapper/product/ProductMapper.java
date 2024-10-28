package com.pttem.ecommerce.product.service.mapper.product;

import com.pttem.ecommerce.product.constant.converter.EnumProductGenderConverter;
import com.pttem.ecommerce.product.domain.base.ProductEntity;
import com.pttem.ecommerce.product.dto.product.ProductDto;
import com.pttem.ecommerce.product.service.mapper.BaseMapper;
import com.pttem.ecommerce.product.service.mapper.config.GeneralMapStructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = GeneralMapStructConfig.class, uses = {
        EnumProductGenderConverter.class
})
public interface ProductMapper extends BaseMapper<ProductEntity, ProductDto> {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

}
