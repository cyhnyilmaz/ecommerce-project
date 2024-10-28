package com.pttem.ecommerce.user.dto.user;

import com.pttem.ecommerce.user.dto.base.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Product dto representing a product")
public class UserDto extends BaseDto {
    @Schema(description = "User's mail", example = "abc@mail.com")
    private String email;

    @Schema(description = "User's password", example = "****")
    private String password;

    @Schema(description = "User's full name", example = "John Smith")
    private String fullName;

    @Schema(description = "User's country", example = "Turkey")
    private String country;

    @Schema(description = "User's phone", example = "5541254785")
    private String phone;

    @Schema(description = "User's default billing address", example = "{id: 1}")
    private UserAddressDto defaultBillingAddress;

    @Schema(description = "User's default shipping address", example = "{id: 2}")
    private UserAddressDto defaultShippingAddress;
}