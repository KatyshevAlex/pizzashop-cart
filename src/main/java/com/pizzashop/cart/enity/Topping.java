package com.pizzashop.cart.enity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@Data
@NoArgsConstructor
public class Topping {
    Long id;
    String name;
    Double price;
    Integer calories;
    private Collection<Pizza> pizzas;
}
