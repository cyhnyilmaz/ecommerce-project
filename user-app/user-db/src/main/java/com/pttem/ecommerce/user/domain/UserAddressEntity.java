package com.pttem.ecommerce.user.domain;

import com.pttem.ecommerce.user.domain.base.BaseEntity;
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
@Table(name = "user_address")
public class UserAddressEntity extends BaseEntity {
    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "town", nullable = false)
    private String town;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "address_context_1")
    private String addressContext1;

    @Column(name = "address_context_2")
    private String addressContext2;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "phone")
    private String phone;
}
