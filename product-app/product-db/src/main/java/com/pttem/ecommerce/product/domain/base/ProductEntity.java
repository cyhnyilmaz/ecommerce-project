package com.pttem.ecommerce.product.domain.base;

import com.pttem.ecommerce.product.constant.enums.EnumProductGender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity{
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "key")
    private String key;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "gender")
    private EnumProductGender gender;

//    @OneToOne
//    @JoinColumn(name = "category", referencedColumnName = "id")
//    private ProductCategoryEntity category;

    @Column(name = "stock")
    private Long stock;

    @Version
    private int version; // Optimistic Locking için gerekli alan

}
