package com.pttem.ecommerce.product.constant.enums;

import lombok.Getter;

@Getter
public enum EnumCrudExceptionMessages {

    NO_SUCH_USER("EnumExceptionMessages.NO_SUCH_USER"),
    HAS_NO_AUTH_FOR_USER_OPERATION("EnumExceptionMessages.HAS_NO_AUTH_FOR_USER_OPERATION"),
    AUTH_NOT_BE_EMPTY_DURING_CRUD("EnumExceptionMessages.AUTH_NOT_BE_EMPTY_DURING_CRUD"),
    OBJECT_NOT_BE_EMPTY("EnumExceptionMessages.OBJECT_NOT_BE_EMPTY"),
    ID_NOT_BE_EMPTY("EnumExceptionMessages.ID_NOT_BE_EMPTY"),
    OBJECT_OR_ID_NOT_BE_EMPTY_DURING_UPDATE("EnumExceptionMessages.OBJECT_OR_ID_NOT_BE_EMPTY_DURING_UPDATE"),
    ID_NOT_BE_EMPTY_FOR_DELETE("EnumExceptionMessages.ID_NOT_BE_EMPTY_FOR_DELETE"),
    ENTITY_NOT_FOUND_FOR_DELETE("EnumExceptionMessages.ENTITY_NOT_FOUND_FOR_DELETE");

    private final String languageKey;
    EnumCrudExceptionMessages(String languageKey) {
        this.languageKey=languageKey;
    }
}
