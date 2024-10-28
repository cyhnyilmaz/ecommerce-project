package com.pttem.ecommerce.user.constant.enums;

public enum EnumOrderStatus {
    HOLD,
    CANCELLED,
    COMPLETED;

    public static EnumOrderStatus valueOfIndex(int index) {
        for (EnumOrderStatus d1 : values()) {
            if (d1.ordinal() == index) {
                return d1;
            }
        }
        return null;
    }

    public static EnumOrderStatus indexOfValue(Integer value) {
        for (EnumOrderStatus d1 : values()) {
            if (d1.ordinal() == value) {
                return d1;
            }
        }
        return null;
    }
}
