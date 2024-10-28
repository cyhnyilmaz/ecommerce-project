package com.pttem.ecommerce.order.domain;

import com.pttem.ecommerce.order.constant.enums.EnumOrderStatus;
import com.pttem.ecommerce.order.domain.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "shipping_address")
    private Long shippingAddress;

    @Column(name = "order_email")
    private String orderEmail;

    @Column(name = "order_status")
    private EnumOrderStatus orderStatus;

    @Version
    private int version;
}
