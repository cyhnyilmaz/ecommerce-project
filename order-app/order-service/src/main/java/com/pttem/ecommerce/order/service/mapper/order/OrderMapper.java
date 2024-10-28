package com.pttem.ecommerce.order.service.mapper.order;

import com.pttem.ecommerce.order.constant.converter.EnumOrderStatusConverter;
import com.pttem.ecommerce.order.domain.OrderEntity;
import com.pttem.ecommerce.order.dto.order.OrderDto;
import com.pttem.ecommerce.order.service.mapper.BaseMapper;
import com.pttem.ecommerce.order.service.mapper.config.GeneralMapStructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = GeneralMapStructConfig.class, uses = {
        EnumOrderStatusConverter.class
})
public interface OrderMapper extends BaseMapper<OrderEntity, OrderDto> {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

}
