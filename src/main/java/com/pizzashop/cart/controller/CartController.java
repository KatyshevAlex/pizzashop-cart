package com.pizzashop.cart.controller;


import com.pizzashop.cart.annotations.LogExecutionTime;
import com.pizzashop.cart.enity.Customer;
import com.pizzashop.cart.enity.Pizza;
import com.pizzashop.cart.enity.Topping;
import com.pizzashop.cart.service.interfaces.ICartService;
import com.pizzashop.cart.service.interfaces.IDaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class CartController {
    private final String authHeader = "Authorization-jwt";

    ICartService mainService;
    IDaoService daoService;

    @GetMapping("/test")
    public Pizza test(){
        Topping tp1 = new Topping();
        tp1.setName("cheese");
        tp1.setPrice(0.5);
        tp1.setCalories(300);

        daoService.addTopping(tp1);

        Topping tp2 = new Topping();
        tp2.setName("pepperoni");
        tp2.setPrice(1.5);
        tp2.setCalories(500);

        daoService.addTopping(tp2);

        Pizza p = new Pizza();
        p.setToppings(daoService.getAllToppings());
        return daoService.savePizza(p);

    }

    @DeleteMapping("/delete-from-cart/{id}")
    @ResponseStatus(HttpStatus.OK)
    @LogExecutionTime
    public List<Pizza> delete(@RequestHeader(value=authHeader) String token, @PathVariable("id") Long id){
        return mainService.removeFromCart(token, id);
    }

    @PostMapping("/add-to-cart")
    @ResponseStatus(HttpStatus.CREATED)
    @LogExecutionTime
    @ApiOperation(value = "Add new pizza to the cart")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Pizza was added"),
            @ApiResponse(code = 404, message = "Error")
    })
    public List<Pizza> addToCart(@RequestHeader(value=authHeader) String token, @RequestBody Pizza pizza) {
        return mainService.addToCart(token, pizza);
    }

    @PostMapping("/save-unfinished")
    @ResponseStatus(HttpStatus.OK)
    @LogExecutionTime
    @ApiOperation(value = "Save unfinished pizza by token")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Saved"),
            @ApiResponse(code = 404, message = "Error")
    })
    public Customer rememberUnfinishedPizza(@RequestHeader(value=authHeader) String token,@RequestBody Pizza unfinished){
        return mainService.addUnfinishedOrder(token,unfinished);
    }


    @GetMapping("/give-guest-token")
    @ResponseStatus(HttpStatus.OK)
    @LogExecutionTime
    @ApiOperation(value = "pre-save customer")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Saved"),
            @ApiResponse(code = 404, message = "Error")
    })
    public String giveGuestToken(){
        String token = mainService.rememberCustomer();
        return token;
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    @LogExecutionTime
    @ApiOperation(value = "Create or Update a Customer")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Saved"),
            @ApiResponse(code = 404, message = "Error")
    })
    public String signUpCustomer(@RequestHeader(value=authHeader) String token,@RequestBody Customer customer){
        String newToken = mainService.updateCustomer(token, customer);
        return newToken;
    }
}
