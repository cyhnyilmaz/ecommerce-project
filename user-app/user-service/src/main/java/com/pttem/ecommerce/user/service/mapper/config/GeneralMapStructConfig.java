package com.pttem.ecommerce.user.service.mapper.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

/**
 * MapStruct kütüphanesinin konfigürasyon sınıfıdır.
 */
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {
    InstantMapper.class,
    TimestampMapper.class
})
public interface GeneralMapStructConfig {

}
