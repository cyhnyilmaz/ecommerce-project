package com.pttem.ecommerce.product.dto.product;

import com.pttem.ecommerce.product.constant.enums.EnumProductGender;
import com.pttem.ecommerce.product.dto.base.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Product dto representing a product")
public class ProductDto extends BaseDto {
    @Schema(description = "Product's Name", example = "Desk")
    private String name;

    @Schema(description = "Product's description", example = "Wooeden Desk")
    private String description;

    @Schema(description = "Product's Activity", example = "true")
    private Boolean isActive;

    @Schema(description = "Product's Gender Class", example = "    GENDERLESS," +
            "    MALE," +
            "    FEMALE," +
            "    BOY," +
            "    GIRL;")
    private EnumProductGender gender;

    @Schema(description = "Product's category relation", example = "{id : 1 }")
    private ProductCategoryDto category;

    @Schema(description = "Product's stock", example = "15")
    private Long stock;

}
