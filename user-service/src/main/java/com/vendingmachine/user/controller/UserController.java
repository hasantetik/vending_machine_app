package com.vendingmachine.user.controller;

import com.vendingmachine.user.entity.UserEntity;
import com.vendingmachine.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/insert")
    public String saveUser(@RequestBody UserEntity user){
        log.info("inside saveUser method of UserController");
        return userService.saveUser(user);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        log.info("inside deleteUser method of UserController");
        return userService.deleteUser(id);
    }
    @PutMapping("/update")
    public String updateUser(@RequestBody UserEntity user){
        log.info("inside updateUser method of UserController");
        return userService.updateUser(user);
    }
    @GetMapping("/findUserById/{id}")
    public UserEntity findUserById(@PathVariable Long id){
        log.info("inside findUserById method of UserController");
        return userService.getUserById(id);
    }
    @GetMapping("/getAll")
    public List<UserEntity> getAllUsers(){
        log.info("inside getAllUsers method of UserController");
        return userService.getUsers();
    }
    @PostMapping("/deposit")
    public String deposit(@RequestParam Long userId,@RequestParam int deposit){
        log.info("inside deposit method of UserController");
        return userService.deposit(userId,deposit);
    }
    @PostMapping("/buy/{productId}/{amount}")
    public String getProduct(@PathVariable("productId") Long productId,@PathVariable("amount") int amount,@RequestParam Long userId){
        log.info("inside getProduct method of UserController");
        return userService.getProduct(productId,amount,userId);
    }
    @GetMapping("/resetDeposit/{userId}")
    public String resetDeposit(@PathVariable("userId") Long id){
        return userService.resetDeposit(id);
    }

}
