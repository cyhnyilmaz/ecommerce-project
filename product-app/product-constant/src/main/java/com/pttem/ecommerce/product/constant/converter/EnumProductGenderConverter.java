package com.pttem.ecommerce.product.constant.converter;

import com.pttem.ecommerce.product.constant.enums.EnumProductGender;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter(autoApply = true)
public class EnumProductGenderConverter implements AttributeConverter<EnumProductGender, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EnumProductGender attribute) {
        if (Objects.isNull(attribute)) {
            return null;
        }
        return attribute.ordinal();
    }

    @Override
    public EnumProductGender convertToEntityAttribute(Integer dbData) {
        if (Objects.isNull(dbData)) {
            return null;
        }
        return EnumProductGender.valueOfIndex(dbData);
    }
}
