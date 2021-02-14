package com.pizzashop.cart.service;


import com.pizzashop.cart.enity.Customer;
import com.pizzashop.cart.enity.Pizza;
import com.pizzashop.cart.service.feign.LoginFeign;
import com.pizzashop.cart.service.interfaces.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private LoginFeign loginFeign;

    @Override
    public List<Pizza> addToCart(String token, Pizza pizza) {
        Customer customer = null;
        if(token != null && !token.isEmpty()) {
            customer = loginFeign.getCustomerByToken(token);
        }

        if(customer != null){
            List<Pizza> cart = customer.getCart();
            cart.add(pizza);
            loginFeign.saveCustomerByToken(token, customer);
        }
        return customer.getCart();
    }

    @Override
    public Customer addUnfinishedOrder(String token, Pizza unfinished) {
        Customer customer = null;
        if(token != null && !token.isEmpty()) {
            customer = loginFeign.getCustomerByToken(token);
        }

        if(customer != null){
            customer.setNotFinished(unfinished);
            loginFeign.saveCustomerByToken(token, customer);
        }
        return customer;
    }

    @Override
    public List<Pizza> removeFromCart(String token, Long id) {
        Customer customer = null;
        if(token != null && !token.isEmpty()) {
            customer = loginFeign.getCustomerByToken(token);
        }


        if(customer != null){
            List<Pizza> cart = customer.getCart();
            int odd = -1;
            if(cart != null){
                for(int i = 0; i < cart.size(); i++){
                    if(cart.get(i).getId().equals(id)){
                        odd = i;
                        break;
                    }
                }
                if(odd>=0)
                    cart.remove(odd);
            }

            loginFeign.saveCustomerByToken(token, customer);
            return cart;
        }

        return new ArrayList<>();
    }

    @Override
    public String rememberCustomer(){
        return loginFeign.getGuestToken();
    }

    @Override
    public String updateCustomer(String token, Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        Customer remembered = null;
        if(token != null && !token.isEmpty()) {
            remembered = loginFeign.getCustomerByToken(token);
        }

        if(remembered != null) {
            customer.setId(remembered.getId());
            customer.setCart(remembered.getCart());
            customer.setNotFinished(remembered.getNotFinished());
        }
        return loginFeign.saveCustomerByToken(token, customer);
    }
}



