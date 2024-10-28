package com.pttem.ecommerce.user.dto.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
public class BaseDto<T extends Serializable> implements Serializable {
    private Long id;
    private Instant createdOn;
    private Instant updateOn;
}
