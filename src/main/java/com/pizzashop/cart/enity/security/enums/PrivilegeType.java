package com.pizzashop.cart.enity.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum PrivilegeType {
    USE_CART,
    PAY,
    ADMINISTRATE
}