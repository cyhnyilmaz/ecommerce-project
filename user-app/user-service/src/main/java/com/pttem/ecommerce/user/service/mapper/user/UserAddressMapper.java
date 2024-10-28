package com.pttem.ecommerce.user.service.mapper.user;

import com.pttem.ecommerce.user.domain.UserAddressEntity;
import com.pttem.ecommerce.user.dto.user.UserAddressDto;
import com.pttem.ecommerce.user.service.mapper.BaseMapper;
import com.pttem.ecommerce.user.service.mapper.config.GeneralMapStructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = GeneralMapStructConfig.class)
public interface UserAddressMapper extends BaseMapper<UserAddressEntity, UserAddressDto> {
    UserAddressMapper INSTANCE = Mappers.getMapper(UserAddressMapper.class);

}
