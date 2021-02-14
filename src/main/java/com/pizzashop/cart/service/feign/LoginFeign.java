package com.pizzashop.cart.service.feign;

import com.pizzashop.cart.enity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "login")
@Service
@RequestMapping("/security")
public interface LoginFeign {
    String authHeader = "Authorization-jwt";

    @RequestMapping(method = RequestMethod.GET, value = "/get-customer-by-token")
    Customer getCustomerByToken(@RequestHeader(authHeader) String token);

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    String saveCustomerByToken(@RequestHeader(authHeader) String token,@RequestBody Customer customer);

    @RequestMapping(method = RequestMethod.GET, value = "/signup")
    String getGuestToken();

}
