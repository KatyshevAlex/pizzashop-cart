package com.pizzashop.cart.service.feign;


import com.pizzashop.cart.enity.Pizza;
import com.pizzashop.cart.enity.Topping;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "repository")
@Service
@RequestMapping("/dao")
public interface DaoFeign {

    @RequestMapping(method = RequestMethod.POST, value = "/add-new-topping")
    void addNewTopping(@RequestBody Topping topping);

    @RequestMapping(method = RequestMethod.GET, value = "/toppings")
    List<Topping> getAllToppings();

    @RequestMapping(method = RequestMethod.POST, value = "/save-pizza")
    Pizza savePizza(@RequestBody Pizza p);
}
