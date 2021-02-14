package com.pizzashop.cart.service;

import com.pizzashop.cart.enity.Pizza;
import com.pizzashop.cart.enity.Topping;
import com.pizzashop.cart.service.feign.DaoFeign;
import com.pizzashop.cart.service.interfaces.IDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DaoService implements IDaoService {
    @Autowired
    DaoFeign daoFeign;

    @Override
    public Pizza savePizza(Pizza p){
        return daoFeign.savePizza(p);
    }

    @Override
    public void addTopping(Topping topping){
        daoFeign.addNewTopping(topping);
    }

    @Override
    public List<Topping> getAllToppings(){
       return daoFeign.getAllToppings();
    }
}
