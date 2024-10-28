package com.pttem.ecommerce.product.constant.enums;

import lombok.Getter;

@Getter
public enum EnumExceptionMessages {

    OBJECT_CANNOT_FOUND("EnumExceptionMessages.OBJECT_CANNOT_FOUND"),
    HAS_NO_AUTH_FOR_USER_OPERATION("EnumExceptionMessages.HAS_NO_AUTH_FOR_USER_OPERATION"),
    OUT_OF_STOCK("EnumExceptionMessages.OUT_OF_STOCK"),
    ;

    private final String languageKey;
    EnumExceptionMessages(String languageKey) {
        this.languageKey=languageKey;
    }
}
