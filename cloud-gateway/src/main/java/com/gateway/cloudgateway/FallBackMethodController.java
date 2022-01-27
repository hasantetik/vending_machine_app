package com.gateway.cloudgateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/userServiceFallBack")
    public String userServiceFallBackGetMethod(){
        return "User Service is taking longer than Expacted" + " Please try again later";
    }

    @GetMapping("/productServiceFallBack")
    public String productServiceFallBackGetMethod(){
        return "Product Service is taking longer than Expacted" + " Please try again later";
    }
    @PostMapping("/userServiceFallBack")
    public String userServiceFallBackPostMethod(){
        return "User Service is taking longer than Expacted" + " Please try again later";
    }

    @PostMapping("/productServiceFallBack")
    public String productServiceFallBackPostMethod(){
        return "Product Service is taking longer than Expacted" + " Please try again later";
    }

}
