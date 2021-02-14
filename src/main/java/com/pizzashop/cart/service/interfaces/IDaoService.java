package com.pizzashop.cart.service.interfaces;

import com.pizzashop.cart.enity.Pizza;
import com.pizzashop.cart.enity.Topping;

import java.util.List;

public interface IDaoService {
    void addTopping(Topping topping);
    List<Topping> getAllToppings();
    Pizza savePizza(Pizza p);
}
