package com.pttem.ecommerce.product.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderTopicDto {
    private Long orderId;
    private Long productId;
    private int quantity;
}
