package com.pizzashop.cart.enity.security;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.pizzashop.cart.enity.Customer;
import com.pizzashop.cart.enity.security.enums.RoleType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@Data
@NoArgsConstructor
public class Role {

    public Role(RoleType rt){
        this.roleType = rt;
    }


    private Long id;
    private RoleType roleType;
    private Collection<Customer> customers;
    private Collection<Privilege> privileges;
}
