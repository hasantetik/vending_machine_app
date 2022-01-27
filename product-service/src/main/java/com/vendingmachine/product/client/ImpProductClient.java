package com.vendingmachine.product.client;

import com.vendingmachine.product.dto.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ImpProductClient implements ProductClient{
    @Autowired
    private ProductClient productClient;
    @Override
    public List<User> getAll() {
        return productClient.getAll();
    }

    @Override
    public User getUserById(Long id) {
        return productClient.getUserById(id);
    }
}
