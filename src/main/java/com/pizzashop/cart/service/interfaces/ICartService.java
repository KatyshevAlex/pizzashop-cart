package com.pizzashop.cart.service.interfaces;


import com.pizzashop.cart.enity.Customer;
import com.pizzashop.cart.enity.Pizza;

import java.util.List;

public interface ICartService {
    List<Pizza> addToCart(String token, Pizza pizza);
    List<Pizza> removeFromCart(String token, Long id);
    String rememberCustomer();
    String updateCustomer(String token, Customer customer);

    Customer addUnfinishedOrder(String token, Pizza unfinished);
}
