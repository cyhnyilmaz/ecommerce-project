package com.pttem.ecommerce.user.dto.user;

import com.pttem.ecommerce.user.dto.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddressDto extends BaseDto {
    private String country;

    private String city;

    private String town;

    private String district;

    private String addressContext1;

    private String addressContext2;

    private String postCode;

    private String phone;
}