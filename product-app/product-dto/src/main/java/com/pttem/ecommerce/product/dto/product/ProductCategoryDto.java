package com.pttem.ecommerce.product.dto.product;

import com.pttem.ecommerce.product.dto.base.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Product Category dto representing  categories")
public class ProductCategoryDto extends BaseDto {
    @Schema(description = "Category Name", example = "Home Appliances")
    private String name;

    @Schema(description = "Category Name", example = "Home Appliances Category")
    private String description;

    @Schema(description = "Category Key", example = "home")
    private String key;

    @Schema(description = "Category Activity", example = "true")
    private Boolean isActive;

    @Schema(description = "Category ParentId", example = "{id: 0}")
    private ProductCategoryDto parentId;
}
