package com.pttem.ecommerce.user.domain;

import com.pttem.ecommerce.user.domain.base.BaseEntity;
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
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Column(name = "email")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "phone")
    private String phone;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "default_billing_address", referencedColumnName = "id")
    private UserAddressEntity defaultBillingAddress;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "default_shipping_address", referencedColumnName = "id")
    private UserAddressEntity defaultShippingAddress;


}
