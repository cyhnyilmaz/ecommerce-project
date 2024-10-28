package com.pttem.ecommerce.order.dto.order;

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

    public OrderTopicDto(OrderDto orderDto) {
        this.orderId=orderDto.getId();
        this.productId=orderDto.getProductId();
        this.quantity=orderDto.getQuantity();

    }
}
