
package com.pttem.ecommerce.order.constant.converter;

import com.pttem.ecommerce.order.constant.enums.EnumOrderStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter(autoApply = true)
public class EnumOrderStatusConverter implements AttributeConverter<EnumOrderStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EnumOrderStatus attribute) {
        if (Objects.isNull(attribute)) {
            return null;
        }
        return attribute.ordinal();
    }

    @Override
    public EnumOrderStatus convertToEntityAttribute(Integer dbData) {
        if (Objects.isNull(dbData)) {
            return null;
        }
        return EnumOrderStatus.valueOfIndex(dbData);
    }
}
