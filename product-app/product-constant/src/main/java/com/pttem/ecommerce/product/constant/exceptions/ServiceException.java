package com.pttem.ecommerce.product.constant.exceptions;

import com.pttem.ecommerce.product.constant.enums.EnumCrudExceptionMessages;
import com.pttem.ecommerce.product.constant.enums.EnumExceptionMessages;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {
    private Boolean warning;

    private final String message;
    private final String messageLanguageKey;

    public ServiceException(EnumExceptionMessages enumException, String detailedMessage) {
        super(enumException.getLanguageKey() + " Detailed message: " + detailedMessage);
        this.warning = Boolean.FALSE;
        this.messageLanguageKey = enumException.getLanguageKey();
        this.message = enumException.getLanguageKey() + " Detailed message: " + detailedMessage;
    }

    public ServiceException(EnumExceptionMessages enumException) {
        super(enumException.getLanguageKey());
        this.warning = Boolean.FALSE;
        this.messageLanguageKey = enumException.getLanguageKey();
        this.message = enumException.getLanguageKey();
    }

    public ServiceException(EnumCrudExceptionMessages enumException) {
        super(enumException.getLanguageKey());
        this.warning = Boolean.FALSE;
        this.messageLanguageKey = enumException.getLanguageKey();
        this.message = enumException.getLanguageKey();
    }


}