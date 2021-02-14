package com.pizzashop.cart.enity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor

public class Pizza implements Serializable {
    Long id;
    List<Topping> toppings;
    Customer customer;
}
