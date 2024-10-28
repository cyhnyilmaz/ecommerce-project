package com.pttem.ecommerce.order.constant.enums;

import lombok.Getter;

@Getter
public enum EnumExceptionMessages {

    OBJECT_CANNOT_FOUND("EnumExceptionMessages.OBJECT_CANNOT_FOUND"),
    HAS_NO_AUTH_FOR_USER_OPERATION("EnumExceptionMessages.HAS_NO_AUTH_FOR_USER_OPERATION"),
    ;

    private final String languageKey;
    EnumExceptionMessages(String languageKey) {
        this.languageKey=languageKey;
    }
}
