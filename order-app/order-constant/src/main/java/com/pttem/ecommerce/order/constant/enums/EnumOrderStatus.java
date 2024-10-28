package com.pttem.ecommerce.order.constant.enums;

public enum EnumOrderStatus {
    RECEIVED,
    OUT_OF_STOCK,
    COMPLETED,
    CANCELLED;

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
