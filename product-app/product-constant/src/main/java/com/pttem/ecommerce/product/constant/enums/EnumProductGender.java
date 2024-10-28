package com.pttem.ecommerce.product.constant.enums;

public enum EnumProductGender {
    GENDERLESS,
    MALE,
    FEMALE,
    BOY,
    GIRL;

    public static EnumProductGender valueOfIndex(int index) {
        for (EnumProductGender d1 : values()) {
            if (d1.ordinal() == index) {
                return d1;
            }
        }
        return null;
    }

    public static EnumProductGender indexOfValue(Integer value) {
        for (EnumProductGender d1 : values()) {
            if (d1.ordinal() == value) {
                return d1;
            }
        }
        return null;
    }
}
