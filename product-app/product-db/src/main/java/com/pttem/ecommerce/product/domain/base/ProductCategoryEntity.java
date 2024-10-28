package com.pttem.ecommerce.product.domain.base;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_category")
public class ProductCategoryEntity extends BaseEntity{
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "key")
    private String key;

    @Column(name = "parent_id")
    private ProductCategoryEntity parentId;

    @Column(name = "is_active")
    private Boolean isActive;
}
