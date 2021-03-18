package com.pizzashop.cart.controller;

import com.pizzashop.cart.enity.Pizza;
import com.pizzashop.cart.service.interfaces.ICartService;
import com.pizzashop.cart.service.interfaces.IDaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartControllerTest {
    @Mock
    ICartService cartService;
    @Mock
    IDaoService daoService;

    @InjectMocks
    CartController cartController;


    @BeforeEach
    void setUp() {
    }

    @Test
    void delete() {

        Pizza pizza = new Pizza();
        when(cartService.removeFromCart("token", 1L))
                .thenReturn(Collections.singletonList(pizza));

        List<Pizza> result = cartController.delete("token",1L);
        assertSame(pizza,result.get(0));
    }
}