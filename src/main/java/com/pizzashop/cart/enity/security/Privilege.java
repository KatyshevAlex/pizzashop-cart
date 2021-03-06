package com.pizzashop.cart.enity.security;

import com.pizzashop.cart.enity.security.enums.PrivilegeType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@Data
@NoArgsConstructor
public class Privilege {

    public Privilege(PrivilegeType pt){
        this.privilegeType = pt;
    }
    private Long id;
    private PrivilegeType privilegeType;
    private Collection<Role> roles;
}