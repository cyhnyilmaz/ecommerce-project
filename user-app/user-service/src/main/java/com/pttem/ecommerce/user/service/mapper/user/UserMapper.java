package com.pttem.ecommerce.user.service.mapper.user;

import com.pttem.ecommerce.user.domain.UserEntity;
import com.pttem.ecommerce.user.dto.user.UserDto;
import com.pttem.ecommerce.user.service.mapper.BaseMapper;
import com.pttem.ecommerce.user.service.mapper.config.GeneralMapStructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = GeneralMapStructConfig.class)
public interface UserMapper extends BaseMapper<UserEntity, UserDto> {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

}
