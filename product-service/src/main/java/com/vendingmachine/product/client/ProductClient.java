package com.vendingmachine.product.client;

import com.vendingmachine.product.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "http://localhost:1453/users")
public interface ProductClient {

    @GetMapping(path = "/getAll")
    List<User> getAll();

    @GetMapping(path = "/findUserById/{id}")
    User getUserById(@PathVariable(name = "id") Long id);
}
