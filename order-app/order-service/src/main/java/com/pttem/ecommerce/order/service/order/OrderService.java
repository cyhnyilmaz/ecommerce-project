package com.pttem.ecommerce.order.service.order;

import com.pttem.ecommerce.order.dto.order.OrderDto;
import com.pttem.ecommerce.order.service.base.BaseCrudService;

public interface OrderService extends BaseCrudService<OrderDto> {

    OrderDto placeOrder(OrderDto orderDto);
}
