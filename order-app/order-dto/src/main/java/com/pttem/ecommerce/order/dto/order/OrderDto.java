package com.pttem.ecommerce.order.dto.order;

import com.pttem.ecommerce.order.constant.enums.EnumOrderStatus;
import com.pttem.ecommerce.order.dto.base.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Order dto representing a order")
public class OrderDto extends BaseDto {
    @Schema(description = "Customer Who Placed Order", example = "1")
    private Long customerId;

    @Schema(description = "Product's Relation", example = "1")
    private Long productId;

    @Schema(description = "Product's Price", example = "59.99")
    private Double price;

    @Schema(description = "Order's quantity", example = "59.99")
    private Integer quantity;

    @Schema(description = "Selected shipping address relation", example = "1")
    private Long shippingAddress;

    @Schema(description = "Order's contact mail address", example = "abc@mail.com")
    private String orderEmail;

    @Schema(description = "Order's status", example = "    RECEIVED," +
            "    OUT_OF_STOCK," +
            "    COMPLETED," +
            "    CANCELLED;")
    private EnumOrderStatus orderStatus;
}